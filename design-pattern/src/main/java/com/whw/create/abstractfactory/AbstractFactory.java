package com.whw.create.abstractfactory;

/**
 * 抽象工厂模式
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/22
 */
public class AbstractFactory {

    public static void main(String[] args) {
        Factory1 factory1 = new Factory11();
        Factory1 factory2 = new Factory12();

        factory1.createProduct1().display();
        factory1.createProduct2().display();

        factory2.createProduct1().display();
        factory2.createProduct2().display();
    }
}



interface Product1 {
    void display();
}

class Product11 implements Product1{
    @Override
    public void display() {
        System.out.println("this is product11");
    }
}

class Product12 implements Product1{
    @Override
    public void display() {
        System.out.println("this is product12");
    }
}

interface Product2 {
    void display();
}

class Product21 implements Product2{
    @Override
    public void display() {
        System.out.println("this is product21");
    }
}

class Product22 implements Product2{
    @Override
    public void display() {
        System.out.println("this is product22");
    }
}


interface Factory1 {
    Product1 createProduct1();
    Product2 createProduct2();
}


class Factory11 implements Factory1{
    @Override
    public Product1 createProduct1() {
        return new Product11();
    }

    @Override
    public Product2 createProduct2() {
        return new Product21();
    }
}

class Factory12 implements Factory1{
    @Override
    public Product1 createProduct1() {
        return new Product12();
    }

    @Override
    public Product2 createProduct2() {
        return new Product22();
    }
}