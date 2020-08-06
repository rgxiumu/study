package com.whw.concurrent;

/**
 * 交替输出
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/7/29
 */
public class Test4 {

    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify(1, 5);

        Thread t1 = new Thread(() -> {
            waitNotify.print("a", 1, 2);
        }, "t1");
        Thread t2 = new Thread(() -> {
            waitNotify.print("b", 2, 3);
        }, "t2");
        Thread t3 = new Thread(() -> {
            waitNotify.print("c", 3, 1);
        }, "t3");

        t1.start();
        t2.start();
        t3.start();


    }

}

class WaitNotify{

    private int flag;

    private int loop;

    public WaitNotify(int flag, int loop) {
        this.flag = flag;
        this.loop = loop;
    }

    public void print(String str, int currentFlag, int nextFlag) {
        for (int i = 0; i < loop; i++) {
            synchronized (this) {
                while (flag != currentFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }

}
