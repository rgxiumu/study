package com.whw.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/6
 */
public class MyUnsafeAccessor {

    static Unsafe UNSAFE;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static Unsafe getUNSAFE() {
        return UNSAFE;
    }
}


