package com.pockocmoc.task_manager.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Аспект для отслеживания действий пользователей.
 */
@Aspect
@Component
@Slf4j
public class UserActionAspect {

    /**
     * Логирует действие пользователя перед вызовом метода, помеченного аннотацией @TrackUserAction.
     *
     * @param joinPoint точка соединения, на которой применяется аспект
     */
    @Before("@annotation(com.pockocmoc.task_manager.annotation.TrackUserAction)")
    public void logUserAction(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        log.info("User action: Method '{}' in class '{}' called with arguments: {}", methodName, className, args);
    }

    /**
     * Логирует результат действия пользователя после вызова метода, помеченного аннотацией @TrackUserAction.
     *
     * @param joinPoint точка соединения, на которой применяется аспект
     * @param result    результат выполнения метода
     */
    @AfterReturning(pointcut = "@annotation(com.pockocmoc.task_manager.annotation.TrackUserAction)", returning = "result")
    public void logUserActionResult(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.info("User action: Method '{}' in class '{}' returned: {}", methodName, className, result);
    }
}
