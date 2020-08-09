package com.whw.concurrent.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class LockTest {

    public static void main(String[] args) {
        MyLock lock = new MyLock();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("running1....");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            } finally {
                lock.unlock();
            }


        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("running2....");
            } finally {
                lock.unlock();
            }


        }, "t2").start();
    }
}


class MyLock implements Lock {

    private MySync mySync = new MySync();

    @Override
    public void lock() {
        mySync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

        mySync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return mySync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return mySync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        mySync.release(1);
    }

    @Override
    public Condition newCondition() {
        return mySync.newCondition();
    }

    class MySync extends AbstractQueuedSynchronizer {


        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override   //是否占有锁
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition() {
            return new ConditionObject();
        }
    }


}