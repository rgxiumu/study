package com.whw.structural.decorator;

/**
 * 装饰器模式
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/23
 */
public class Decorator {


    public static void main(String[] args) {
        Compent compent1 = new CompentImpl1();
        Compent compent2 = new CompentImpl2();

        Compent decorator1 = new Decorator1(compent2);

        Compent decorator2 = new Decorator2(compent1);

        decorator1.display();
        decorator2.display();
    }
}


interface Compent{
    void display();
}

class CompentImpl1 implements Compent{
    @Override
    public void display() {
        System.out.println("原本方法1");
    }
}

class CompentImpl2 implements Compent{
    @Override
    public void display() {
        System.out.println("原本方法2");
    }
}

class DecoratorF implements Compent{
    private Compent compent;

    public DecoratorF(Compent compent) {
        this.compent = compent;
    }
    @Override
    public void display() {
        compent.display();
    }
}


class Decorator1 extends DecoratorF{

    public Decorator1(Compent compent) {
        super(compent);
    }

    @Override
    public void display() {
        addFunction();
        super.display();
    }

    private void addFunction(){
        System.out.println("myFunction1");
    }
}


class Decorator2 extends DecoratorF{

    public Decorator2(Compent compent) {
        super(compent);
    }

    @Override
    public void display() {
        addFunction();
        super.display();
    }

    private void addFunction(){
        System.out.println("myFunction2");
    }
}