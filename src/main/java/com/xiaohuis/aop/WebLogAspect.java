package com.xiaohuis.aop;

import com.xiaohuis.entity.AllLog;
import com.xiaohuis.service.AllLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;


/**
 * <p> 日志切面 </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/10/26 14:56
 */

@Component
@Aspect
public class WebLogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AllLogService allLogService;

    @Pointcut("execution(* com.xiaohuis.controller..*.*(..))")
    public void ControllerLog(){}


    @Around("ControllerLog()")
    public Object logAroundController(ProceedingJoinPoint point) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 打印请求相关参数
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        long timeCost = System.currentTimeMillis() - startTime;
        String url = request.getRequestURL().toString();
        String httpMethod = request.getMethod();
        String ip = request.getRemoteAddr();
        String classMethod = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
        String browser = userAgent.getBrowser().toString();
        String os = userAgent.getOperatingSystem().toString();

        AllLog allLog = new AllLog(ip, url, httpMethod, classMethod, timeCost, os, browser, header, new Date());
        allLogService.addAllLog(allLog);

        logger.info("-----------TIME-COST----------->" + timeCost + " ms");
        logger.info("-----------URL----------------->" + url);
        logger.info("-----------HTTP_METHOD--------->" + httpMethod);
        logger.info("-----------IP------------------>" + ip);
        logger.info("-----------CLASS_METHOD-------->" + classMethod);
        logger.info("-----------ARGS---------------->" + Arrays.toString(point.getArgs()));
        logger.info("-----------USER-AGENT---------->" + header);
        logger.info("-----------BROWSER------------->" + browser);
        logger.info("-----------OS------------------>" + os);
        logger.info("-------------------------------------------------------------------------------------------------------------");
        return result;
    }
}
