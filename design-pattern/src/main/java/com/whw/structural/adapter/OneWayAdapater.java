package com.whw.structural.adapter;

/**
 * 单项适配器
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/22
 */
public class OneWayAdapater {

    public static void main(String[] args) {
        Target target = new Adapater();
        target.display();
    }

}


class Adaptee{

    public void display(){
        System.out.println("正真的调用方法执行");
    }
}

interface Target{
    void display();
}

class Adapater implements Target {

    private Adaptee adaptee;

    public Adapater() {
        this.adaptee = new Adaptee();
    }

    @Override
    public void display() {
        adaptee.display();
    }
}