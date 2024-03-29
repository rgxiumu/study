package com.whw.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/10/15
 */
public class DynamicJDKProxyDemo {

    public static void main(String[] args) {
        MyInvocationHandler myh = new MyInvocationHandler(new ActualImpl());
        Actual actual = (Actual) Proxy.newProxyInstance(DynamicJDKProxyDemo.class.getClassLoader(), new Class[]{Actual.class}, myh);
        actual.test1();
        actual.test2();

//        Class<?> proxyClass = Proxy.getProxyClass(DynamicJDKProxyDemo.class.getClassLoader(), Actual.class);
//
//        try {
//            Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
//            Actual actual1 = (Actual)constructor.newInstance((InvocationHandler) (proxy, method, args1) -> {
//                System.out.println("aaasssd");
//                return null;
//            });
//            actual1.test1();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}

class MyInvocationHandler implements InvocationHandler {


    private Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start---");
        Object invoke = method.invoke(object, args);
        System.out.println("end---");
        return invoke;
    }
}
interface Actual {
    void test1();
    void test2();
}
class ActualImpl implements Actual {
    @Override
    public void test1() {
        System.out.println("test1");
    }
    @Override
    public void test2() {
        System.out.println("test2");
    }
}


