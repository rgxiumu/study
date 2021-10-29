package com.whw.test;

import com.whw.controller.StudentController;
import com.whw.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/11/11
 */
@SpringBootTest
public class SpelTest {


    @Autowired
    public StudentService studentService;
    @Autowired
    public StudentController studentController;


    @Test
    public void test2() {
        studentService.createStudents(2, "202010293");
    }
    @Test
    public void test21() {
        studentController.test();
    }

    @Test
    public void test3() {

//        ExpressionParser parser = new SpelExpressionParser();
//        Expression expression = parser.parseExpression("('Hello' + ' World').concat(#end)");
//        EvaluationContext context = new StandardEvaluationContext();
//        context.setVariable("end", "!");
//        System.out.println(expression.getValue(context));

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("新增学生信息，学校#{#schoolId}, 学号#{#studentNo}", new TemplateParserContext());

        EvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("schoolId", "www");
        evaluationContext.setVariable("studentNo", "22aaqww");

        String value = expression.getValue(evaluationContext, String.class);
        System.out.println(value);


//        //创建解析器
//        SpelExpressionParser parser = new SpelExpressionParser();
//        //创建解析器上下文
//        ParserContext context = new TemplateParserContext("%{", "}");
//        Expression expression = parser.parseExpression("你好:%{#name},我们正在学习:%{#lesson}", context);
//
//        //创建表达式计算上下文
//        EvaluationContext evaluationContext = new StandardEvaluationContext();
//        evaluationContext.setVariable("name", "路人甲java");
//        evaluationContext.setVariable("lesson", "spring高手系列!");
//        //获取值
//        String value = expression.getValue(evaluationContext, String.class);
//        System.out.println(value);
    }


    @Test
    public void test4() throws NoSuchMethodException {
        EvaluationContext evaluationContext = new StandardEvaluationContext();

        Method getString = SpelTest.class.getMethod("getString", String.class);
        ((StandardEvaluationContext) evaluationContext).setVariable("getString", getString);

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("新增学生信息，学校#{#schoolId}, 学号#{#studentNo}, #{#getString('whw')}", new TemplateParserContext());
        evaluationContext.setVariable("schoolId", "2");
        evaluationContext.setVariable("studentNo", "22aaqww");
        Object value = expression.getValue(evaluationContext);
        System.out.println(value.toString());
    }


    public static String getString(String str){
        return "hello " + str;
    }
}
