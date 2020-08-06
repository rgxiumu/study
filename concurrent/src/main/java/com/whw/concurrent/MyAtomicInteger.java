package com.whw.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/6
 */
public class MyAtomicInteger {

    public static void main(String[] args) {

        for (int k = 0; k < 10000; k++) {
            Account account = new AtomicAccount(new AtomicInteger(10000));
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

class AtomicAccount implements Account {

    private AtomicInteger balance;

    public AtomicAccount(AtomicInteger balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void drawBalance(int value) {
        balance.getAndAdd(value);
    }
}
