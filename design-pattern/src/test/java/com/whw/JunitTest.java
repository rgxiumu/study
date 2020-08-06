package com.whw;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试junitdemo
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/30
 */
public class JunitTest {

    @BeforeEach
    public void beforeEach(){
        System.out.println("BeforeEach");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("AfterEach");
    }
    @BeforeAll
    public static void beforeAll(){
        System.out.println("BeforeAll");
    }
    @AfterAll
    public static void afterAll(){
        System.out.println("AfterAll");
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void test1(){
        System.out.println("test1");
    }

    @DisabledOnJre(JRE.OTHER)
    @Test
    public void test2(){
        System.out.println("test2");
    }


    @DisabledIfEnvironmentVariable(named = "DEBUG", matches = "true")
    @Test
    public void test3(){
        System.out.println("test3");
    }

    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    @Test
    public void test4(){
        System.out.println(java.time.LocalDate.now().getDayOfWeek() == java.time.DayOfWeek.THURSDAY);
    }

    @Test
    @EnabledIf({"java.time.LocalDate.now().getDayOfWeek() == java.time.DayOfWeek.SUNDAY"})
    void test5(){
        System.out.println("test5");
    }

    @ParameterizedTest
    @MethodSource
    public void test6(String x, String y){
        System.out.println(x);
        System.out.println(y);
    }

    static List<Arguments> test6(){
        List<Arguments> arguments = new ArrayList<>();
         arguments.add(Arguments.arguments("1", "a"));
        arguments.add(Arguments.arguments("2", "b"));
        return arguments;
    }


    @ParameterizedTest
    @CsvSource({"33,zz", "55,kk"})
    public void test7(String x, String y){
        System.out.println(x);
        System.out.println(y);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/aas.csv"})
    public void test8(String x, String y){
        System.out.println(x);
        System.out.println(y);
    }
}
