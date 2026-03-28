package com.cts.openbankx;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger log =
            LoggerFactory.getLogger(LoggerAspect.class);

    /*
     * Pointcut for ALL service methods in your project
     */
    @Pointcut("execution(* com.example.openbankx.service..*(..))")
    public void allServiceMethods() {}

    // ---------------- BEFORE ----------------
    @Before("allServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info(
                "Entering method: {}.{}()",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName()
        );
    }

    // ---------------- AFTER ----------------
    @After("allServiceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        log.info(
                "Exiting method: {}.{}()",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName()
        );
    }

    // ---------------- AFTER RETURNING (SUCCESS) ----------------
    @AfterReturning(
            pointcut = "allServiceMethods()",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        log.info(
                "SUCCESS : {}.{}()",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName()
        );

        log.info("RETURN  : {}", result);
    }

    // ---------------- AFTER THROWING (EXCEPTION) ----------------
    @AfterThrowing(
            pointcut = "allServiceMethods()",
            throwing = "ex"
    )
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {

        log.error(
                "EXCEPTION in {}.{}() : {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                ex.getMessage()

        );
    }
}