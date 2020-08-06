package com.whw.create.singleton;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * 如果编写的是多线程程序，则不要删除上例代码中的关键字 volatile 和 synchronized，否则将存在线程非安全的问题。
 * 如果不删除这两个关键字就能保证线程安全，但是每次访问时都要同步，会影响性能，且消耗更多的资源，这是懒汉式单例的缺点
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/22
 */
@Slf4j
public class Lazy {

    //volatile 保证所有线程下同步
    private static volatile Lazy lazy = null;
    //防止被外部实例化
    private Lazy() {
    }

    public static synchronized Lazy getInstance(){
        if(null == lazy){
            lazy = new Lazy();
        }
        return lazy;
    }

}
