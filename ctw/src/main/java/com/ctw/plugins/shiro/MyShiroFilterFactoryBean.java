package com.ctw.plugins.shiro;

import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.servlet.Filter;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ctw com.ctw.plugins.shiro
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/16 12: 25
 * @Version 1.0
 * @explain： 自定义的 KitShiroFilterFactoryBean, 使用了自定义的serveletresponse, 避免重定向到登录页面后, url里出现JSESSIONID=xxx
 */
public class MyShiroFilterFactoryBean implements FactoryBean, BeanPostProcessor {

    private static transient final Logger log = LoggerFactory.getLogger(MyShiroFilterFactoryBean.class);

    private SecurityManager securityManager;

    private Map<String, Filter> filters;

    private Map<String, String> filterChainDefinitionMap; //urlPathExpression_to_comma-delimited-filter-chain-definition

    private String loginUrl;
    private String successUrl;
    private String unauthorizedUrl;

    private AbstractShiroFilter instance;

    public MyShiroFilterFactoryBean() {
        this.filters = new LinkedHashMap<String, Filter>();
        this.filterChainDefinitionMap = new LinkedHashMap<String, String>(); //order matters!
    }

    /**
     * Sets the application {@code SecurityManager} instance to be used by the constructed Shiro Filter.  This is a
     * required property - failure to set it will throw an initialization exception.
     *
     * @return the application {@code SecurityManager} instance to be used by the constructed Shiro Filter.
     */
    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    /**
     * Sets the application {@code SecurityManager} instance to be used by the constructed Shiro Filter.  This is a
     * required property - failure to set it will throw an initialization exception.
     *
     * @param securityManager the application {@code SecurityManager} instance to be used by the constructed Shiro Filter.
     */
    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }


    public String getLoginUrl() {
        return loginUrl;
    }
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
    public String getSuccessUrl() {
        return successUrl;
    }
    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }
    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }
    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }
    public Map<String, Filter> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Filter> filters) {
        this.filters = filters;
    }

    public Map<String, String> getFilterChainDefinitionMap() {
        return filterChainDefinitionMap;
    }

    public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
        this.filterChainDefinitionMap = filterChainDefinitionMap;
    }

    public void setFilterChainDefinitions(String definitions) {
        Ini ini = new Ini();
        ini.load(definitions);
        //did they explicitly state a 'urls' section?  Not necessary, but just in case:
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            //no urls section.  Since this _is_ a urls chain definition property, just assume the
            //default section contains only the definitions:
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        setFilterChainDefinitionMap(section);
    }


    public Object getObject() throws Exception {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    public Class getObjectType() {
        return MyShiroFilterFactoryBean.class;
    }


    public boolean isSingleton() {
        return true;
    }

    protected FilterChainManager createFilterChainManager() {

        DefaultFilterChainManager manager = new DefaultFilterChainManager();
        Map<String, Filter> defaultFilters = manager.getFilters();
        //apply global settings if necessary:
        for (Filter filter : defaultFilters.values()) {
            applyGlobalPropertiesIfNecessary(filter);
        }

        //Apply the acquired and/or configured filters:
        Map<String, Filter> filters = getFilters();
        if (!CollectionUtils.isEmpty(filters)) {
            for (Map.Entry<String, Filter> entry : filters.entrySet()) {
                String name = entry.getKey();
                Filter filter = entry.getValue();
                applyGlobalPropertiesIfNecessary(filter);
                if (filter instanceof Nameable) {
                    ((Nameable) filter).setName(name);
                }
                //'init' argument is false, since Spring-configured filters should be initialized
                //in Spring (i.e. 'init-method=blah') or implement InitializingBean:
                manager.addFilter(name, filter, false);
            }
        }

        //build up the chains:
        Map<String, String> chains = getFilterChainDefinitionMap();
        if (!CollectionUtils.isEmpty(chains)) {
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue();
                manager.createChain(url, chainDefinition);
            }
        }

        return manager;
    }


    protected AbstractShiroFilter createInstance() throws Exception {

        log.debug("Creating Shiro Filter instance.");

        SecurityManager securityManager = getSecurityManager();
        if (securityManager == null) {
            String msg = "SecurityManager property must be set.";
            throw new BeanInitializationException(msg);
        }

        if (!(securityManager instanceof WebSecurityManager)) {
            String msg = "The security manager does not implement the WebSecurityManager interface.";
            throw new BeanInitializationException(msg);
        }

        FilterChainManager manager = createFilterChainManager();

        //Expose the constructed FilterChainManager by first wrapping it in a
        // FilterChainResolver implementation. The AbstractShiroFilter implementations
        // do not know about FilterChainManagers - only resolvers:
        PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
        chainResolver.setFilterChainManager(manager);

        //Now create a concrete ShiroFilter instance and apply the acquired SecurityManager and built
        //FilterChainResolver.  It doesn't matter that the instance is an anonymous inner class
        //here - we're just using it because it is a concrete AbstractShiroFilter instance that accepts
        //injection of the SecurityManager and FilterChainResolver:
        return new SpringShiroFilter((WebSecurityManager) securityManager, chainResolver);
    }

    private void applyLoginUrlIfNecessary(Filter filter) {
        String loginUrl = getLoginUrl();
        if (StringUtils.hasText(loginUrl) && (filter instanceof AccessControlFilter)) {
            AccessControlFilter acFilter = (AccessControlFilter) filter;
            //only apply the login url if they haven't explicitly configured one already:
            String existingLoginUrl = acFilter.getLoginUrl();
            if (AccessControlFilter.DEFAULT_LOGIN_URL.equals(existingLoginUrl)) {
                acFilter.setLoginUrl(loginUrl);
            }
        }
    }

    private void applySuccessUrlIfNecessary(Filter filter) {
        String successUrl = getSuccessUrl();
        if (StringUtils.hasText(successUrl) && (filter instanceof AuthenticationFilter)) {
            AuthenticationFilter authcFilter = (AuthenticationFilter) filter;
            //only apply the successUrl if they haven't explicitly configured one already:
            String existingSuccessUrl = authcFilter.getSuccessUrl();
            if (AuthenticationFilter.DEFAULT_SUCCESS_URL.equals(existingSuccessUrl)) {
                authcFilter.setSuccessUrl(successUrl);
            }
        }
    }

    private void applyUnauthorizedUrlIfNecessary(Filter filter) {
        String unauthorizedUrl = getUnauthorizedUrl();
        if (StringUtils.hasText(unauthorizedUrl) && (filter instanceof AuthorizationFilter)) {
            AuthorizationFilter authzFilter = (AuthorizationFilter) filter;
            //only apply the unauthorizedUrl if they haven't explicitly configured one already:
            String existingUnauthorizedUrl = authzFilter.getUnauthorizedUrl();
            if (existingUnauthorizedUrl == null) {
                authzFilter.setUnauthorizedUrl(unauthorizedUrl);
            }
        }
    }

    private void applyGlobalPropertiesIfNecessary(Filter filter) {
        applyLoginUrlIfNecessary(filter);
        applySuccessUrlIfNecessary(filter);
        applyUnauthorizedUrlIfNecessary(filter);
    }

    /**
     * Inspects a bean, and if it implements the {@link Filter} interface, automatically adds that filter
     * instance to the internal {@link #setFilters(Map) filters map} that will be referenced
     * later during filter chain construction.
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Filter) {
            log.debug("Found filter chain candidate filter '{}'", beanName);
            Filter filter = (Filter) bean;
            applyGlobalPropertiesIfNecessary(filter);
            getFilters().put(beanName, filter);
        } else {
            log.trace("Ignoring non-Filter bean '{}'", beanName);
        }
        return bean;
    }

    /**
     * Does nothing - only exists to satisfy the BeanPostProcessor interface and immediately returns the
     * {@code bean} argument.
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * Ordinarily the {@code AbstractShiroFilter} must be subclassed to additionally perform configuration
     * and initialization behavior.  Because this {@code FactoryBean} implementation manually builds the
     * {@link AbstractShiroFilter}'s
     * {@link AbstractShiroFilter#setSecurityManager(org.apache.shiro.web.mgt.WebSecurityManager) securityManager} and
     * {@link AbstractShiroFilter#setFilterChainResolver(org.apache.shiro.web.filter.mgt.FilterChainResolver) filterChainResolver}
     * properties, the only thing left to do is set those properties explicitly.  We do that in a simple
     * concrete subclass in the constructor.
     */
    private static final class SpringShiroFilter extends AbstractShiroFilter {

        protected SpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {
            super();
            if (webSecurityManager == null) {
                throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
            }
            setSecurityManager(webSecurityManager);
            if (resolver != null) {
                setFilterChainResolver(resolver);
            }
        }

        @Override
        protected ServletResponse wrapServletResponse(HttpServletResponse orig, ShiroHttpServletRequest request) {
            return new MyShiroHttpServletResponse(orig, getServletContext(), request);
        }
    }
}