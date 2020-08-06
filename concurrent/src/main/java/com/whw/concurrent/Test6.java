package com.whw.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/7/30
 */
public class Test6 {

    public static void main(String[] args) {

        MyReentrantLock lock = new MyReentrantLock(5);
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        Thread t1 = new Thread(() -> {
            lock.print("a", c1, c2);
        }, "t1");
        Thread t2 = new Thread(() -> {
            lock.print("b", c2, c3);
        }, "t2");
        Thread t3 = new Thread(() -> {
            lock.print("c", c3, c1);
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        c1.signal();
        lock.unlock();
    }
}

class MyReentrantLock extends ReentrantLock {

    private int loop;

    public MyReentrantLock(int loop) {
        this.loop = loop;
    }

    public void print(String str, Condition currCondition, Condition nextCondition) {

        for (int i = 0; i < loop; i++) {
            lock();

            try {
                currCondition.await();

                System.out.print(str);

                nextCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }

        }
    }





}
