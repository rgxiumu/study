package com.whw.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * 交替输出
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/7/30
 */
public class Test5 {

    static Thread t1;
    static Thread t2;
    static Thread t3;


    public static void main(String[] args) {
        ParkUnPark parkUnPark = new ParkUnPark(5);
        t1 = new Thread(() -> {
            parkUnPark.print("a", t2);
        }, "t1");
        t2 = new Thread(() -> {
            parkUnPark.print("b", t3);
        }, "t2");
        t3 = new Thread(() -> {
            parkUnPark.print("c", t1);
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);
    }
}

class ParkUnPark {

    private int loop;

    public ParkUnPark(int loop) {
        this.loop = loop;
    }

    public void print(String str, Thread nextThread) {
        for (int i = 0; i < loop; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(nextThread);
        }
    }
}
