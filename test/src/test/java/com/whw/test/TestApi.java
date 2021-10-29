package com.whw.test;

import com.chiway.service.SendMessage;
import com.whw.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/11/11
 */
@SpringBootTest
public class TestApi {


    @Autowired
    public SendMessage sendMessage;

    @Autowired
    public StudentService studentService;



    @Test
    public void test1() {
        String s = sendMessage.sendMessages("1");
        System.out.println(s);
    }

    @Test
    public void test2() {
        studentService.createStudents(2, "202010293");
    }

    @Test
    public void test3() {

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("('新增学生信息，学校').concat(#schoolId).concat(', 学号').concat(#studentNo)");
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("schoolId", "2");
        evaluationContext.setVariable("studentNo", "22aaqww");
        Object value = expression.getValue(evaluationContext);
        System.out.println(value.toString());
    }
}
