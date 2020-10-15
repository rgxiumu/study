package com.whw;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/10/15
 */
public class AdapterDemo {

    public static void main(String[] args) {
        B b = new BImpl();
        AB ab = new AB(b);
        ab.am1();
        ab.am2();
    }

}

interface A {
    void am1();
    void am2();
}

interface B {
    void bm1();
    void bm2();
}

class BImpl implements B {
    @Override
    public void bm1() {
        System.out.println("this is b method1");
    }

    @Override
    public void bm2() {
        System.out.println("this is b method2");
    }
}


class AB implements A{

    private B b;

    public AB(B b) {
        this.b = b;
    }

    @Override
    public void am1() {
        b.bm1();
    }

    @Override
    public void am2() {
        b.bm2();
    }
}