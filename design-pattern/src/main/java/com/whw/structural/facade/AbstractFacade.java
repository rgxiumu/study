package com.whw.structural.facade;

/**
 * 抽象外观类
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/23
 */
public class AbstractFacade {

    public static void main(String[] args) {
        AbstractFacadeAll a = new AbstractFacadeA();
        a.myMethod();

        AbstractFacadeAll b = new AbstractFacadeB();
        b.myMethod();

    }
}

interface AbstractFacadeAll{
    void myMethod();
}

class AbstractFacadeA implements  AbstractFacadeAll{

    private Sub1 sub1;

    private Sub2 sub2;

    public AbstractFacadeA() {
        this.sub1 = new Sub1();
        this.sub2 = new Sub2();
    }

    @Override
    public void myMethod() {
        System.out.println("AbstractFacadeA");
        sub1.run1();
        sub2.run2();
    }
}


class AbstractFacadeB implements  AbstractFacadeAll{

    private Sub3 sub3;

    private Sub2 sub2;

    public AbstractFacadeB() {
        this.sub3 = new Sub3();
        this.sub2 = new Sub2();
    }

    @Override
    public void myMethod() {
        System.out.println("AbstractFacadeB");
        sub3.run3();
        sub2.run2();
    }
}
