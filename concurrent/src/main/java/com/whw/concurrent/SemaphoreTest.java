package com.whw.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/11
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(() -> {
            try {
                semaphore.acquire();
                System.out.println("11111");
                Thread.sleep(1000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.submit(() -> {

            try {
                semaphore.acquire();
                System.out.println("222222");
                Thread.sleep(1000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.submit(() -> {

            try {
                semaphore.acquire();
                System.out.println("33333");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
        });

        pool.shutdown();
    }
}
