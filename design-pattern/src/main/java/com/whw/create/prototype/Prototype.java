package com.whw.create.prototype;

import lombok.Data;

/**
 * 原型模式
 * 用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或相似的新对象
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/22
 */
public class Prototype {

    public static void main(String[] args) throws CloneNotSupportedException {
        Student student = new Student();
        student.setAge(10);
        student.setClassName("Grade1Class1");
        student.setName("zhangsan");
        Student student1 = (Student) student.clone();
        student1.setName("lisi");
        student.display();
        student1.display();
    }
}

@Data
class Student implements Cloneable{

    private String name;

    private String className;

    private int age;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void display(){
        System.out.println(name+":"+age+":"+className);
    }
}