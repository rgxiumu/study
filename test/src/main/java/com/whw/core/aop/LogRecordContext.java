package com.whw.core.aop;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/9/30
 */
public class LogRecordContext {

    private static final InheritableThreadLocal<Stack<Map<String, Object>>> THREAD_LOCAL = new InheritableThreadLocal<>();


    public static void putEmptySpan(){
        Stack<Map<String, Object>> stack = THREAD_LOCAL.get();
        if (stack == null) {
            stack = new Stack<>();
        }
        stack.add(new HashMap<>());
        THREAD_LOCAL.set(stack);
    }

    //设置线程需要保存的值
    public static void setValue (String key, Object value) {
        THREAD_LOCAL.get().peek().put(key, value);
    }
    //获取线程中保存的值
    public static Map<String, Object> getValue() {
        return THREAD_LOCAL.get().pop();
    }
    //移除线程中保存的值
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
