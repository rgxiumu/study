package com.whw.structural.proxy;

/**
 * 静态代理
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/22
 */
public class StaticProxy {

    public static void main(String[] args) {
        ShopProxy shopProxy = new ShopProxy();
        shopProxy.shopName();
    }
}


interface Shopping{
    void shopName();
}


class Walmart implements Shopping{
    @Override
    public void shopName() {
        System.out.println("this is walmart!!!");
    }
}


class ShopProxy implements Shopping{

    private Walmart walmart;

    @Override
    public void shopName() {
        preShop();
        if(null == walmart){
            walmart = new Walmart();
        }
        walmart.shopName();
        afterShop();
    }

    public void preShop(){
        System.out.println("prepare");
    }


    public void afterShop(){
        System.out.println("after");
    }
}