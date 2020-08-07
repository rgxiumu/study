package com.whw.concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/7
 */
public class Test {

    public static void main(String[] args) {
//        ThreadPool threadPool = new ThreadPool(2, 2, 1000, TimeUnit.MILLISECONDS);
//
//        for (int i = 0; i < 5; i++) {
//            int j = i;
//            threadPool.execute(()->{
//                System.out.println("task" + j);
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            });
//        }


        ThreadPool threadPoolReject = new ThreadPool(2, 2, 1000, TimeUnit.MILLISECONDS, (queue, task)->{
//            死等
//            queue.put(task);
//            超时等待
//            queue.offer(task, 1000, TimeUnit.MILLISECONDS);
//            放弃执行
//            System.out.println("放弃执行");
//            抛出异常
//            throw new RuntimeException("抛出异常");
//            自己执行
            task.run();
        });

        for (int i = 0; i < 5; i++) {
            int j = i;
            threadPoolReject.execute(()->{
                System.out.println("task" + j);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
    }
}
