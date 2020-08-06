package com.whw.create.singleton;

/**
 * 饿汉式单例在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以是线程安全的，可以直接用于多线程而不会出现问题。
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/22
 */
public class Hungry {

    private static Hungry hungry = new Hungry();

    private Hungry() {
    }

    public static Hungry getInstance(){
        return hungry;
    }
}
