package com.whw.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/12
 */
public class HashMapTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();


        Map<String, Integer> map2 = new Hashtable<>();

        map2.put("q", 1);


        Map<String, Integer> map1 = Collections.synchronizedMap(map);


        Map<String, Integer> map3 = new ConcurrentHashMap<>();

        map3.put("aa", 1);
        System.out.println(map3.get("aa"));


        Thread.holdsLock(HashMapTest.class);
    }
}
