package com.whw.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/14
 */
@Data
public class Test1 {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        Student s1 = new Test1().new Student("s1", "1");
        Student s2 = new Test1().new Student("s2", "2");
        Student s3 = new Test1().new Student("s3", "3");
        list.add(s1);
        list.add(s2);
        list.add(s3);
        long count = list.stream().map(Student::getIdNum).distinct().count();
        if (count != list.size()) {
            throw new RuntimeException("队列异常");
        }
        Map<String, Student> collect = list.stream().collect(Collectors.toMap(Student::getIdNum, s -> s));
        collect.forEach((k, v) -> System.out.println(k + "----" + v));
    }


    @Data
    @AllArgsConstructor
    class Student{
        private String name;
        private String idNum;
    }

    @Test
    public void test1(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(now.toLocalDate(), LocalTime.MAX);
        long expire = Duration.between(now, localDateTime).toMillis();
        System.out.println(now);
        System.out.println(localDateTime);
        System.out.println(expire);


        System.out.println(String.format("%03d", 1));
        System.out.println(String.format("%03d", 10));
        System.out.println(String.format("%03d", 101));
        System.out.println(String.format("%03d", 1011));
    }

    @Test
    public void test2(){
        BigDecimal r = new BigDecimal(1);
        for (int i = 1; i <=1000 ; i++) {
            r = r .multiply(new BigDecimal(i)) ;
        }
        System.out.println(r);
    }

    @Test
    public void test3(){
        Boolean b1 = true;
        Boolean b2 = !b1;
        System.out.println(b2);
    }



    @Test
    public void test4() throws Exception{
        Test1 str = new Test1();

        ClassLoader classLoader = str.getClass().getClassLoader();

        ClassLoader classLoader1 = Test1.class.getClassLoader();

        Class<?> aClass = Class.forName("com.whw.test.Test1");

        ClassLoader classLoader2 = aClass.getClassLoader();

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader == classLoader1);
        System.out.println(classLoader == classLoader2);
        System.out.println(classLoader == contextClassLoader);

    }
}
