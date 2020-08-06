package com.whw.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/7/29
 */
public class Test3 {



    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            LockSupport.park();
            System.out.println(1);
        }, "t1");


        Thread t2 = new Thread(() -> {
            System.out.println(2);
            LockSupport.unpark(t1);
        }, "t2");

        t1.start();
        t2.start();
    }
}
