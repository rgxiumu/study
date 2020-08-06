package com.whw.behavior.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 在 Java 中，通过 java.util.Observable 类和 java.util.Observer 接口定义了观察者模式
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/24
 */
public class ObserverTest {

    public static void main(String[] args) {
        Observerable observerable = new Observerable();
        Observer test1 = new Test1();
        Observer test2 = new Test2();
        observerable.addObserver(test1);
        observerable.addObserver(test2);
        observerable.change(new int[]{1,2,3});
    }
}


class Test1 implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Test1 get Notice"+ arg);
    }
}

class Test2 implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Test1 get Notice" + arg);
    }
}


class Observerable extends Observable{

    public void change(int[] price){
        super.setChanged();
        super.notifyObservers(price);
    }

}