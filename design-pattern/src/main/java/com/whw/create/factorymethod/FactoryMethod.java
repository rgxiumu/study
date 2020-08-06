package com.whw.create.factorymethod;

/**
 * 工厂方法模式
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/22
 */
public class FactoryMethod {



    public static void main(String[] args) {
        Factory factory = new MobileFactory();
        factory.createProduct().display();
        Factory factory1 = new ComputerFactory();
        factory1.createProduct().display();
    }

}

interface Product {
    void display();
}

class Mobile implements Product{
    @Override
    public void display() {
        System.out.println("this is mobile!!!");
    }
}

class Computer implements Product{

    @Override
    public void display() {
        System.out.println("this is computer!!!");
    }
}

interface Factory {

    Product createProduct();
}

class MobileFactory implements Factory{
    @Override
    public Product createProduct() {
        return new Mobile();
    }
}

class ComputerFactory implements Factory{
    @Override
    public Product createProduct() {
        return new Computer();
    }
}
