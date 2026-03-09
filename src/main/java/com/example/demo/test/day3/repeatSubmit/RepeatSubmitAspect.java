package com.example.demo.test.day3.repeatSubmit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepeatSubmitAspect {

    @Pointcut(value = "@annotation(repeatSubmit)")
    public void pointCutRepeatSubmit(RepeatSubmit repeatSubmit) {

    }

    @Around(value = "pointCutRepeatSubmit(repeatSubmit)", argNames = "joinPoint,repeatSubmit")
    public Object around(ProceedingJoinPoint joinPoint, RepeatSubmit repeatSubmit) {

        return null;
    }
}
