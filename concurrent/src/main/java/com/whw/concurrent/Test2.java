package com.whw.concurrent;

/**
 * 同步模式之顺序控制
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/7/29
 */
public class Test2 {


    static boolean isPrint2 = false;
    static final Object lock = new Object();



    public static void main(String[] args) {



        Thread t1 = new Thread(() -> {

            synchronized(lock) {
                while (!isPrint2) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(1);
            }
        }, "t1");


        Thread t2 = new Thread(() -> {

            synchronized (lock) {
                System.out.println(2);
                isPrint2 = true;
                lock.notifyAll();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
