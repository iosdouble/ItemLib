package com.nh.limit.common.aspect;

import com.nh.limit.common.annotation.ControllerEndpoint;
import com.nh.limit.common.exception.FebsException;
import com.nh.limit.common.utils.FebsUtil;
import com.nh.limit.common.utils.HttpContextUtil;
import com.nh.limit.monitor.service.ILogService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Classname ControllerEndpointAspect
 * @Description TODO
 * @Date 2019/10/30 3:05 PM
 * @Created by nihui
 */
@Aspect
@Component
public class ControllerEndpointAspect extends AspectSupport {

    @Autowired
    private ILogService logService;

    @Pointcut("@annotation(com.nh.limit.common.annotation.ControllerEndpoint)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws FebsException {
        Object result;
        Method targetMethod = resolveMethod(point);
        ControllerEndpoint annotation = targetMethod.getAnnotation(ControllerEndpoint.class);
        String operation = annotation.operation();
        long start = System.currentTimeMillis();
        try {
            result = point.proceed();
            if (StringUtils.isNotBlank(operation)) {
                HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
                logService.saveLog(point, targetMethod, request, operation, start);
            }
            return result;
        } catch (Throwable throwable) {
            String exceptionMessage = annotation.exceptionMessage();
            String message = throwable.getMessage();
            String error = FebsUtil.containChinese(message) ? exceptionMessage + "，" + message : exceptionMessage;
            throw new FebsException(error);
        }
    }
}
