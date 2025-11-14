package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceLoggingAspect {
    @Around("execution(* com.example.service.*.*(..))")
    public Object logBeforeMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() +
                " cost time: " + (end - start) + "ms");
        return proceed;
    }
}
