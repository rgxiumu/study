package com.whw.concurrent.threadpool;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/7
 */
public class ThreadPool {

    private BlockQueue<Runnable> queue;

    private HashSet<Worker> workers = new HashSet<>();

    private int capacity;

    private int queueCapacity;

    private long timeout;

    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int capacity, int queueCapacity, long timeout, TimeUnit timeUnit, RejectPolicy<Runnable> rejectPolicy) {
        this.capacity = capacity;
        this.queueCapacity = queueCapacity;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
        this.queue = new BlockQueue<>(this.queueCapacity);
    }

    public ThreadPool(int capacity, int queueCapacity, long timeout, TimeUnit timeUnit) {
        this.capacity = capacity;
        this.queueCapacity = queueCapacity;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.queue = new BlockQueue<>(this.queueCapacity);
    }

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < capacity) {
                System.out.println("新建worker");
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else {
//                queue.put(task);
//                queue.offer(task, timeout, timeUnit);
                queue.tryPut(rejectPolicy, task);
            }
        }

    }

    class Worker extends Thread{

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {

//            while (null != task || (task = queue.take()) != null) {
            while (null != task || (task = queue.poll(timeout, timeUnit)) != null) {
                try {
                    System.out.println("执行任务"+task);
                    task.run();
                } finally {
                    task = null;
                }
            }

            synchronized (workers) {
                System.out.println("移除工作线程"+task);
                workers.remove(this);
            }
        }
    }
}


