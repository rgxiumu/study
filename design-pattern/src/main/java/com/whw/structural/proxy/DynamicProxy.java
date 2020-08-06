package com.whw.structural.proxy;

import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/22
 */
public class DynamicProxy {

    public static void main(String[] args) {
        Cleaner cleaner = new Cleaner();
        WorkerHandler workerHandler = new WorkerHandler(cleaner);
        Worker invocationHandler = (Worker) Proxy.newProxyInstance(cleaner.getClass().getClassLoader(), cleaner.getClass().getInterfaces(), workerHandler);
        invocationHandler.job("zhansan");
    }

}


interface Worker {

    void job(String name);
}

class Cleaner implements Worker{
    @Override
    public void job(String name) {
        System.out.println(name+":"+"clean");
    }
}

class Writer implements Worker{
    @Override
    public void job(String name) {
        System.out.println("writer");
    }
}

class WorkerHandler implements InvocationHandler {

    private Object object;

    public WorkerHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("preInvoke");
        method.invoke(object, args);
        System.out.println("afterInvoke");
        return null;
    }
}