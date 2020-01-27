package com.telran.ilcarro.advice;

import com.telran.ilcarro.annotaion.NotNull;
import com.telran.ilcarro.service.exceptions.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author Anton Konkin
 */

@Aspect
@Component
public class ValidatorsAdvice {

    @Around("@annotation(com.telran.ilcarro.annotaion.CheckForNull)")
    public Object nullValueParameterCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] params = joinPoint.getArgs();
        for (Object param : params) {
            if (param == null) {
                throw new ServiceException(String.format("Parameters can't be NULL at ", joinPoint.getSignature()));
            }
            Field[] paramFields = param.getClass().getDeclaredFields();
            for (Field paramField : paramFields) {
                Annotation[] annotations = paramField.getDeclaredAnnotations();
                paramField.setAccessible(true);
                for (Annotation annotation : annotations) {
                    if (annotation instanceof NotNull) {
                        Object obj = paramField.get(param);
                        if (obj == null) {
                            throw new ServiceException(String.format("Field %s can't be NULL", paramField.getName()));
                        }
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
}