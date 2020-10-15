package com.whw;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/10/15
 */
public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.whw.Student");
        Student student = (Student) clazz.newInstance();
        student.setName("aaaa");
        Method[] declaredMethods = clazz.getDeclaredMethods();
//        for (Method declaredMethod : declaredMethods) {
//            declaredMethod.invoke(student, null);
//        }

//        Method getName = clazz.getMethod("getName");
//        Object invoke = getName.invoke(student);
//        System.out.println(invoke.toString());
        Method getName = clazz.getMethod("setName", String.class);
        Object invoke = getName.invoke(student, "bbb");
        System.out.println(student.toString());

//        Field[] declaredFields = clazz.getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            System.out.println(declaredField.getName() + "--" + declaredField.getType());
//            declaredField.setAccessible(true);
//            if (declaredField.getType() == String.class) {
//                declaredField.set(student, "kuli");
//            } else if (declaredField.getType() == Integer.class) {
//                declaredField.set(student, 10);
//            } else if (declaredField.getType() == Date.class) {
//                declaredField.set(student, new Date());
//            }
//        }
//        System.out.println(student.toString());
    }
}

class Student {
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    private String name;
    private Integer age;
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}