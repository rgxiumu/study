package com.whw.concurrent.threadpool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 任务队列
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/7
 */
public class BlockQueue<T> {


    private Deque<T> deque = new ArrayDeque<>();

    private ReentrantLock lock = new ReentrantLock();

    private Condition fullWait = lock.newCondition();

    private Condition emptyWait = lock.newCondition();

    private int capacity;

    public BlockQueue(int capacity) {
        this.capacity = capacity;
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T t) {
        lock.lock();
        try {
            if (deque.size() == capacity) {
                System.out.println("队列已满，执行reject。。。"+t);
                rejectPolicy.reject(this, t);
            } else {
                System.out.println("新加任务到队列" + t);
                deque.addLast(t);
                emptyWait.signal();
            }
        } finally {
            lock.unlock();
        }
    }


    public void put(T t) {
        lock.lock();
        try {
            while (deque.size() == capacity) {
                System.out.println("队列已满，等待。。。"+t);
                fullWait.await();
            }
            System.out.println("新加任务到队列"+t);
            deque.addLast(t);
            emptyWait.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void offer(T t, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (deque.size() == capacity) {
                if (nanos < 0) {
                    System.out.println("队列已满，等待超时，丢弃。。。"+t);
                    return;
                }
                System.out.println("队列已满，等待。。。"+t);
                nanos = fullWait.awaitNanos(nanos);
            }
            System.out.println("新加任务到队列"+t);
            deque.addLast(t);
            emptyWait.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public T take() {
        lock.lock();
        try {
            while (deque.isEmpty()) {
                try {
                    System.out.println("队列已空，等待。。。");
                    emptyWait.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            System.out.println("从队列取出任务"+t);
            fullWait.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public T poll(long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (deque.isEmpty()) {
                try {
                    if (nanos < 0) {
                        System.out.println("队列已空，等待超时。。。返回null，结束");
                        return null;
                    }
                    System.out.println("队列已空，等待。。。");
                    nanos = emptyWait.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            System.out.println("从队列取出任务"+t);
            fullWait.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

}
