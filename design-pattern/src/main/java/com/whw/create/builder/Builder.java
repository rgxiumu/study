package com.whw.create.builder;

import javax.sound.midi.SysexMessage;

/**
 * 建造者模式
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/22
 */
public class Builder {



    public static void main(String[] args) {
        Build builder1 = new Builder1();
        Director director = new Director(builder1);
        Person person = director.createPerson();
        person.display();


        Build builder2 = new Builder2();
        Director director1 = new Director(builder2);
        Person person1 = director1.createPerson();
        person1.display();
    }
}


class Person{

    private String name;
    private int age;
    private int height;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void display(){
        System.out.println(name+":"+age+":"+height);
    }
}


abstract class Build {

    public Person person = new Person();

    abstract void setName();

    abstract void setAge();

    abstract void setHeight();

    public Person getPerson(){
        return person;
    }
}

class Builder1 extends Build{

    @Override
    void setName() {
        person.setName("Builder1Name");
    }

    @Override
    void setAge() {
        person.setAge(1);
    }

    @Override
    void setHeight() {
        person.setHeight(100);
    }
}

class Builder2 extends Build{

    @Override
    void setName() {
        person.setName("Builder2Name");
    }

    @Override
    void setAge() {
        person.setAge(2);
    }

    @Override
    void setHeight() {
        person.setHeight(200);
    }
}


class Director {

    private Build build;

    public Director(Build build) {
        this.build = build;
    }

    public Person createPerson(){
        build.setAge();
        build.setHeight();
        build.setName();
        return build.getPerson();
    }
}