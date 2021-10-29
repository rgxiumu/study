package com.whw.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRecord {

    String operation() default "";

    String createrName() default "";

    Class clazz() default Object.class;

}
