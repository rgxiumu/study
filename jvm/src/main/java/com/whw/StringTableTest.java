package com.whw;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/14
 */
public class StringTableTest {


    public static void main(String[] args) {
//        test1();

    }


    public static void test2() {

    }

    public static void test1() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "a" + "b";
        String s4 = s1 + s2;
        String s5 = "ab";
        String s6 = s4.intern();

        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s3 == s6);
    }
}
