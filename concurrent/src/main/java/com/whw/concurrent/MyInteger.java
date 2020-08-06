package com.whw.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/6
 */
public class MyInteger {

    public static void main(String[] args) {

        for (int j = 0; j < 10000; j++) {
            Account account = new MyAccount(0);
            List<Thread> ts = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                Thread thread = new Thread(()->{
                    account.drawBalance(1);
                }, "t".concat(i+""));
                ts.add(thread);
            }

            for (Thread t : ts) {
                t.start();
            }

            for (Thread t : ts) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(account.getBalance());
        }
    }
}

class MyAccount implements Account{

    private int balance;

    public MyAccount(int balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public void drawBalance(int value) {
        balance += value;
    }
}