package com.whw.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whw.core.aop.LogRecordContext;
import com.whw.entity.StudentTrans;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/9/29
 */
@Component
@Aspect
public class LogRecordAspect {


    private static final Logger LOG = LoggerFactory.getLogger(LogRecordAspect.class);

    private DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    @Around(value = "@annotation(com.whw.aop.LogRecord)")
    public Object logRecord(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("LogRecordAspect++++++++++++++++++++++");
        LogRecordContext.putEmptySpan();
        Object obj = joinPoint.proceed();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LogRecord logRecord = method.getAnnotation(LogRecord.class);
        String operation = logRecord.operation();

        EvaluationContext evaluationContext = new StandardEvaluationContext();

        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        Object[] args = joinPoint.getArgs();
        //方法参数
        for (int i = 0; i < args.length; i++) {
            evaluationContext.setVariable(parameterNames[i], args[i]);
        }
        //日志上下文设置的值
        Map<String, Object> objectMap = LogRecordContext.getValue();
        objectMap.forEach(evaluationContext::setVariable);

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(operation, new TemplateParserContext());
        String value = expression.getValue(evaluationContext, String.class);
        System.out.println(value);

        ObjectMapper objectMapper = new ObjectMapper();
        StudentTrans studentTrans = objectMapper.convertValue(objectMap, StudentTrans.class);

        System.out.println("LogRecordAspect-----------------------");
        return obj;
    }





}
