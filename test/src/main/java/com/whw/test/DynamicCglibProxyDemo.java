package com.whw.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/10/15
 */
public class DynamicCglibProxyDemo {
    public static void main(String[] args) {
        HumanTest human = new HumanTest();
        MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor(human);
        Move o = (Move)Enhancer.create(human.getClass(), human.getClass().getInterfaces(), myMethodInterceptor);
        o.fly();
        o.run();
    }
}

class MyMethodInterceptor implements MethodInterceptor {

    private Object object;

    public MyMethodInterceptor(Object object) {
        this.object = object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("cglib begin...");
        Object invoke = method.invoke(object, objects);
        System.out.println("cglib end...");
        return invoke;
    }
}

interface Move{
    void fly();
    void run();
}

class Human implements Move{

    @Override
    public void fly() {
        System.out.println("human fly");
    }

    @Override
    public void run() {
        System.out.println("human run");
    }
}
