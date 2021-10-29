package com.whw.core.aop;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/9/29
 */
public interface IParseFunction {

    default boolean executeBefore(){
        return false;
    }

    String functionName();

    String apply(String value);
}
