package com.exadel.booking.utils.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class BasicService {

    @Pointcut("bean(*Service)")
    public void annotated() {
    }

    @Before("annotated()")
    public void print(JoinPoint joinPoint) {
        log.info("start method: " + joinPoint.getSignature().getName() +
                " with args : " + printParameters(joinPoint));
    }

    private String printParameters(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String[] names = ((CodeSignature) jp.getSignature()).getParameterNames();
        Class[] types = ((CodeSignature) jp.getSignature()).getParameterTypes();
        StringBuilder sb = new StringBuilder();
        if (args.length == 0) {
            return "No args";
        } else {
            for (int i = 0; i < args.length; i++) {
                sb.append("  " + types[i].getName() +
                        " = " + args[i]);
            }
        }
        return String.valueOf(sb);
    }
}