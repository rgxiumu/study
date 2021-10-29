package com.whw.core.aop;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/9/29
 */
public interface IFunctionService {

    ParseFunctionFactory parse = new ParseFunctionFactory(null);

    String apply(String functionName, String value);

    boolean beforeFunction(String functionName) ;
}
