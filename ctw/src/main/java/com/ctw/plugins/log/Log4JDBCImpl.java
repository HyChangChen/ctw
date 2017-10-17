package com.ctw.plugins.log;

import com.ctw.domain.loginfo.LogInfo;
import com.ctw.domain.user.User;
import com.ctw.plugins.aop.SystemControllerLog;
import com.ctw.plugins.aop.SystemServiceLog;
import com.ctw.service.loginfo.ILogInfoService;
import com.ctw.type.Constants;
import com.ctw.utils.Functions;
import com.ctw.utils.JacksonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.util.JSON;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ctw com.ctw.plugins.log
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/16 16: 44
 * @Version 1.0
 * @explain：............
 */
public class Log4JDBCImpl extends LogAdapter {

    private static final Logger logger = LoggerFactory.getLogger(Log4JDBCImpl.class);



    private LogLevel rootLogLevel = LogLevel.ERROR;


    public ILogInfoService logInfoService;

    private Map<String, LogLevel> customLogLevel = new HashMap<String, LogLevel>();

    /**
     * @param message
     * @param objects
     * @param logLevel
     */
    @Override
    public void log(String message, Object[] objects, LogLevel logLevel) {
        MessageFormat mFormat = new MessageFormat(message);
        String result = mFormat.format(objects);

        if (!StringUtils.isNotBlank(result)) {
            return;
        }
        User shiroUser = Functions.getUserInfo();
        LogInfo logInfo = new LogInfo();
        logInfo.setCreateTime(new Date());
        if (null != shiroUser) {
            logInfo.setIpAddress(shiroUser.getIpAddress());
            logInfo.setUsername(shiroUser.getLoginName());
        }
        logInfo.setMessage(result);
      //  logInfo.setLogLevel(logLevel);

        logInfoService.create(logInfo);
    }

    public void setRootLogLevel(LogLevel rootLogLevel) {
        this.rootLogLevel = rootLogLevel;
    }

    /**
     * @return
     */
    @Override
    public LogLevel getRootLogLevel() {
        return rootLogLevel;
    }

    public void setCustomLogLevel(Map<String, LogLevel> customLogLevel) {
        this.customLogLevel = customLogLevel;
    }

    @Override
    public Map<String, LogLevel> getCustomLogLevel() {
        return customLogLevel;
    }

    public void setLogInfoService(ILogInfoService logInfoService) {
        this.logInfoService = logInfoService;
    }


}
