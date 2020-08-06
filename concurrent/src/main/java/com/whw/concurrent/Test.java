package com.whw.concurrent;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/7/29
 */
public class Test {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("enter sleep");
                    Thread.sleep(2000);
                    System.out.println("sleep over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("do things");
        }, "t1");

        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }


}
