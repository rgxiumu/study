package com.whw.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/11
 */
public class CountdownLatchTest {

    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CountDownLatch countDownLatch = new CountDownLatch(10);
        String[] var = new String[10];
        Random random = new Random();
        for (int j = 0; j < 10; j++) {
            int k = j;
            executorService.submit(() -> {
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(random.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    var[k] = i + "%  ";
                    System.out.print("\r"
                            + var[0]+ var[1]
                            + var[2]+ var[3]
                            + var[4]+ var[5]
                            + var[6]+ var[7]
                            + var[8]+ var[9]);
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\rgame start");
        executorService.shutdown();
    }
}
