package com.evgen.logger;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    @Pointcut("execution(* com.evgen.*.*(..))")
    private void ServiceMethod() { }

    @Around(value = "ServiceMethod()")
    public Object logWebServiceCall(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        Thread curThread = Thread.currentThread();
        LOGGER.info("From Class " + className + " method " + methodName + " was called on thread " + curThread.getName() + " with args " + methodArgs);
        Object result =  joinPoint.proceed();
        LOGGER.info("From Class " + className + " method " + methodName + " returned value = " + result + " on thread " + curThread.getName());

        return result;
    }

}
