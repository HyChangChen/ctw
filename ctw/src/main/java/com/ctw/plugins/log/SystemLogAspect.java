package com.ctw.plugins.log;

import com.ctw.domain.loginfo.LogInfo;
import com.ctw.domain.user.User;
import com.ctw.plugins.aop.SystemControllerLog;
import com.ctw.plugins.aop.SystemServiceLog;
import com.ctw.service.loginfo.ILogInfoService;
import com.ctw.type.Constants;
import com.ctw.utils.HttpRequestUtils;
import com.ctw.utils.JacksonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Date;

/**
 * ctw com.ctw.plugins.log
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/17 22: 03
 * @Version 1.0
 * @explain：............
 */
@Aspect
@Component
@Order(value = 0)
public class SystemLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(Log4JDBCImpl.class);
    @Resource
    private ILogInfoService iLogInfoService;
    private Integer id;
    //程序运行开始时间：
    private long startTime;
    //程序运行结束时间：
    private long endTime;

    //Service层切点
    @Pointcut("@annotation(com.ctw.plugins.aop.SystemServiceLog)")
    public void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("@annotation(com.ctw.plugins.aop.SystemControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        //请求的IP
        String ip = request.getRemoteAddr();
        try {
            //*========控制台输出=========*//
       /*     System.out.println("=====前置通知开始 =====");
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "."
                    + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
            System.out.println("请求人:" + user.getFullName());
            System.out.println("请求IP:" + ip);*/
            //*========数据库日志=========*//
            LogInfo logInfo = new LogInfo();
            logInfo.setMacAddress(HttpRequestUtils.getMacAddress(ip));

            logInfo.setIpAddress(ip);
            logInfo.setFunctionName(joinPoint.getSignature().getName());
            logInfo.setCreateTime(new Date());
            logInfo.setParams(null);
            logInfo.setUsername(user.getFullName());
            MessageFormat mFormat = new MessageFormat(getControllerMethodDescription(joinPoint));
            Object[] object = new Object[]{user.getFullName()};
            String result = mFormat.format(object);
            logInfo.setMessage(result);
            id = iLogInfoService.create(logInfo);
            System.out.println("=====前置通知结束 =====");
        } catch (Exception e) {
            //记录本地异常日志
            logger.error("== 前置通知异常 ==");
            logger.error("异常信息:{}", e.getMessage());
        }
    }

    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
        endTime = System.currentTimeMillis();
        if (null != id) {
            LogInfo logInfo=new LogInfo();
            logInfo.setId(id.longValue());
            logInfo.setContimes((endTime - startTime) + "ms");
            iLogInfoService.update(logInfo);
        }

        System.out.println("程序运行共计耗时：" + (endTime - startTime) + "ms");
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        //获取请求ip
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                try {
                    params += JacksonUtil.mapToJson(joinPoint.getArgs()[i]) + ";";
                } catch (JsonProcessingException e1) {
                    e1.printStackTrace();
                }
            }
        }
        try {
              /*========控制台输出=========*/
            System.out.println(" =====异常通知开始 =====");
            System.out.println("异常代码:" + e.getClass().getName());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "."
                    + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));
            System.out.println("请求人:" + user.getFullName());
            System.out.println("请求IP:" + ip);
            System.out.println("请求参数:" + params);
               /*==========数据库日志=========*/
           /* SysLog log = SpringContextHolder.getBean(SysLog.class);
            log.setDescription(getServiceMethodDescription(joinPoint));
            log.setExceptionCode(e.getClass().getName());
            log.setType("1");
            log.setExceptionDetail(e.getMessage());
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()
                    + "()"));
            log.setParams(params);
            log.setCreateBy(user.getUsername());
            log.setCreateDate(DateUtil.getCurrentDate());
            log.setRequestIp(ip);
            //保存数据库
            logService.insert(log);*/
            System.out.println("=====异常通知结束 =====");
        } catch (Exception ex) {
            //记录本地异常日志
            logger.error("== 异常通知异常 ==");
            logger.error("异常信息:{  }", ex.getMessage());
        }
         /*==========记录本地异常日志==========*/
        logger.error("异常方法:{        } 异常代码: {} 异常信息: {}参数:{}",
                joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(),
                e.getClass().getName(), e.getMessage(), params)
        ;
    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
      //  System.out.println("description:" + description);
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog.class).description();

                    break;
                }
            }
        }

     //   System.out.println("description:" + description);
        return description;
    }
}
