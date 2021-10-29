package com.whw.core.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/9/29
 */
@Component
public class LogRecordValueParser {

    LogRecordExpressionEvaluator logRecordExpressionEvaluator;

    IFunctionService iFunctionService;

    EvaluationContext evaluationContext;
//
//    public EvaluationContext evaluationContext(){
//        logRecordExpressionEvaluator.c
//    }
}
