package com.whw.core.aop;

import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/9/29
 */
@Component
public class StudentParseFunction implements IParseFunction{

    @Override
    public String functionName() {
        return "Student";
    }

    @Override
    public String apply(String value) {
        return value + "Student";
    }
}
