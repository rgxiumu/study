package com.whw.test;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/10/19
 */
public class HumanTest {


    static class WomanTest extends HumanTest{}
    static class ManTest extends HumanTest{}
//
//    public void sayHello(HumanTest humanTest){
//        System.out.println("HumanTest");
//    }
    public void sayHello(WomanTest womanTest){
        System.out.println("WomanTest");
    }
    public void sayHello(ManTest manTest){
        System.out.println("ManTest");
    }
    public void sayHello(Object o){
        System.out.println("Object");
    }


    public static void main(String[] args) {
        HumanTest t1 = new WomanTest();
        HumanTest t2 = new ManTest();
        HumanTest h = new HumanTest();
        h.sayHello(t1);
        h.sayHello(t2);
    }

}
