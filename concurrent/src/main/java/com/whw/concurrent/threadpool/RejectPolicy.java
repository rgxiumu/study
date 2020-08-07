package com.whw.concurrent.threadpool;

/**
 * 拒绝策略
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/7
 */
@FunctionalInterface
public interface RejectPolicy<T> {

    void reject(BlockQueue<T> queue, T task);
}
