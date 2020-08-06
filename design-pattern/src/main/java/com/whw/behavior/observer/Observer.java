package com.whw.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式，当一方变化时，通知观察者
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/24
 */
public class Observer {

    public static void main(String[] args) {
        OutWork outWork = new OutWorkImpl();
        IObserver observer1 = new ObserverImpl1();
        IObserver observer2 = new ObserverImpl2();
        outWork.addObserver(observer1);
        outWork.addObserver(observer2);
        outWork.notifyObservers();
    }
}


abstract class OutWork{
    protected List<IObserver> observers;

    public OutWork() {
        this.observers = new ArrayList<>();
    }

    protected void addObserver(IObserver observer){
        observers.add(observer);
    }

    protected void removeObserver(IObserver iObserver){
        observers.remove(iObserver);
    }

    abstract void notifyObservers();
}
class OutWorkImpl extends OutWork{

    public OutWorkImpl() {
        super();
    }

    @Override
    void notifyObservers() {
        observers.forEach(IObserver::excute);
    }
}


interface IObserver{

    void excute();
}

class ObserverImpl1 implements IObserver{

    @Override
    public void excute() {
        System.out.println("ObserverImpl1 get notice");
    }
}

class ObserverImpl2 implements IObserver{
    @Override
    public void excute() {
        System.out.println("ObserverImpl2 get notice");
    }
}