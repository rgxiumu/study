package com.whw.concurrent;

/**
 * 同步模式之保护性性暂停
 * 一个线程等待另一个线程的执行结果
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/7/29
 */
public class Test1 {

    public static void main(String[] args) {
        GuardedObject object = new GuardedObject();

        Thread t1 = new Thread(() -> {

            String str = object.getString();
            System.out.println(str);
        }, "t1");

        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("set message");
        object.setString("message");
    }
}

class GuardedObject {

    private String result;

    private final Object lock = new Object();


    public String getString() {

        synchronized(lock) {
            while (result == null) {
                System.out.println("waiting...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "The Result is :" + result;
        }

    }

    public void setString(String str) {
        synchronized(lock) {
            result = str;
            lock.notifyAll();
        }
    }
}
