package com.whw.concurrent;

import lombok.Data;
import sun.misc.Unsafe;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/8/6
 */
public class MyUnsafe {

    public static void main(String[] args) throws NoSuchFieldException {
        Student student = new Student();
        Unsafe unsafe = MyUnsafeAccessor.getUNSAFE();
        Long offset = unsafe.objectFieldOffset(Student.class.getDeclaredField("name"));
        Long offsetId = unsafe.objectFieldOffset(Student.class.getDeclaredField("age"));
        unsafe.compareAndSwapObject(student, offset, null, "zhangsan");
        unsafe.compareAndSwapInt(student, offsetId, 0 , 30);
        System.out.println(student.toString());
    }

}

@Data
class Student {

    private String name;

    private int age;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
