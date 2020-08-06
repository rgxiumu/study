package com.whw.structural.adapter;

/**
 * 双向适配器
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/22
 */
public class TwoWayAdapter {

    public static void main(String[] args) {

        TwoWayAdaptee2 twoWayAdaptee2 = new TwoWayAdaptee2(new Adaptee1());
        twoWayAdaptee2.showName();

        TwoWayAdaptee2 twoWayAdaptee21 = new TwoWayAdaptee2(new Adaptee2());
        twoWayAdaptee21.showSecret();
    }
}


interface Target1{
    void showName();
}

class Adaptee1{

    public void showName(){
        System.out.println("Adaptee1");
    }
}

interface Target2{
    void showSecret();
}

class Adaptee2{

    public void showSecret(){
        System.out.println("Adaptee2");
    }
}

class TwoWayAdaptee2 implements Target1, Target2{

    private Adaptee1 adaptee1;
    private Adaptee2 adaptee2;

    public TwoWayAdaptee2(Adaptee2 adaptee2) {
        this.adaptee2 = adaptee2;
    }

    public TwoWayAdaptee2(Adaptee1 adaptee1) {

        this.adaptee1 = adaptee1;
    }

    @Override
    public void showName() {
        adaptee1.showName();
    }

    @Override
    public void showSecret() {
        adaptee2.showSecret();
    }
}