package com.whw.concurrent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/10
 */
public class ExecutorsTest {

    private static AtomicInteger aa = new AtomicInteger();

    public static void main(String[] args) throws IOException {
        System.out.println("111111");
        new Thread(()-> System.out.println("1112222")).start();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                3,
                2,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2), new ThreadFactory() {
            private AtomicInteger atomicInteger = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                System.out.println("create thread");
                return new Thread(r,"tee"+atomicInteger.incrementAndGet());
            }
        });

        threadPoolExecutor.execute(()->{
            aa.incrementAndGet();
            System.out.println("test11111---"+Thread.currentThread());
        });
        threadPoolExecutor.execute(()->{
            aa.incrementAndGet();
            System.out.println("test22222---"+Thread.currentThread());
        });
        threadPoolExecutor.execute(()->{
            aa.incrementAndGet();
            System.out.println("test33333---"+Thread.currentThread());
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(aa.get());
//        Executors.newCachedThreadPool();

        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {

            private AtomicInteger atomicInteger = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "myThread-" + atomicInteger);
            }
        });

        executorService.execute(() -> {
            File file = new File("");
            try {
                String canonicalPath = file.getCanonicalPath();
                System.out.println(canonicalPath);
                System.out.println(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        File file2 = new File("");
        System.out.println(file2.getCanonicalPath());
        System.out.println(file2.getAbsolutePath());
        Class<?> executorsTestClass = ExecutorsTest.class;
        URL resource = executorsTestClass.getResource("/");
        System.out.println(resource.getPath());
        executorService.execute(() -> {
            String filepath = "C:\\Users\\think\\Desktop\\test\\test.txt";

            File file = new File(filepath);

            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write("啊雪儿".getBytes());
            }catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
