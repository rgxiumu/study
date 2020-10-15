package com.whw;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/10/15
 */
public class ProxyDemo {
    public static void main(String[] args) {

        A1 a1 = new A1Impl();
        ProxyA1 pa = new ProxyA1(a1);
        pa.a1();
    }
}

interface A1 {
    void a1();
}

class A1Impl implements A1 {
    @Override
    public void a1() {
        System.out.println("this is a1");
    }
}

class ProxyA1 implements A1{

    private A1 a1;

    public ProxyA1(A1 a1) {
        this.a1 = a1;
    }

    @Override
    public void a1() {
        a1.a1();
    }
}
