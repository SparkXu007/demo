package com.example.demo.test.day3.repeatSubmit;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

    enum Type {PARAM, TOKEN}

    Type limitType() default Type.PARAM;

    long lockTime() default 5L;
}
