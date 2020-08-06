package com.whw.structural.facade;

/**
 * 外观模式，将多个子系统统一接口
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/23
 */
public class Facade {

    public static void main(String[] args) {
        FacadeAll all = new FacadeAll();

        all.method();
    }
}


class FacadeAll{


    private Sub1 sub1;

    private Sub2 sub2;

    private Sub3 sub3;

    public FacadeAll() {
        this.sub1 = new Sub1();
        this.sub2 = new Sub2();
        this.sub3 = new Sub3();
    }

    public void method(){
        System.out.println("this is Facade Method");

        sub1.run1();

        sub2.run2();

        sub3.run3();

    }

}


class Sub1{
    public void run1(){
        System.out.println("this is method 1");
    }
}

class Sub2{
    public void run2(){
        System.out.println("this is method 2");
    }
}

class Sub3{
    public void run3(){
        System.out.println("this is method 3");
    }
}