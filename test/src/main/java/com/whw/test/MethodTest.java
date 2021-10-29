package com.whw.test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/6/3
 */
public class MethodTest {


    public static void main(String[] args) throws Throwable {


        MethodHandles.Lookup lookup = MethodHandles.lookup();

        MethodType methodType1 = MethodType.methodType(void.class, String.class);
        MethodHandle constructor1 = lookup.findConstructor(MethodTarget.class, methodType1);
        Object target1 = constructor1.invoke("aaaa");
        System.out.println(target1);


        MethodType methodType2 = MethodType.methodType(void.class);
        MethodHandle constructor2 = lookup.findConstructor(MethodTarget.class, methodType2);
        Object target2 = constructor2.invoke();
        System.out.println(target2);



        Field field = MethodTarget.class.getDeclaredField("name");
        field.setAccessible(true);
        MethodHandle methodHandle = lookup.unreflectSetter(field);
        methodHandle.invoke(target2, "bbbb");
        System.out.println(target2);


        MethodType methodType4 = MethodType.methodType(String.class);
        MethodHandle getName = lookup.findStatic(MethodTarget.class, "getName", methodType4);
        String invoke4 = (String)getName.invoke();
        System.out.println(invoke4);


        MethodType methodType5 = MethodType.methodType(void.class);
        MethodHandle constructor5 = lookup.findConstructor(MethodTarget2.class, methodType5);
        Object target5 = constructor5.invoke();
        System.out.println(target5);


        MethodType methodType3 = MethodType.methodType(String.class);
        MethodHandle getName3 = lookup.findVirtual(MethodTarget.class, "getName3", methodType3);
        String invoke3 = (String)getName3.invoke(target1);
        System.out.println(invoke3);

        MethodHandle methodHandle5 = getName3.bindTo(target5);
        String invoke5 = (String)methodHandle5.invoke();
        System.out.println(invoke5);
    }
}


class MethodTarget {


    private String name;

    public MethodTarget() {
    }

    public MethodTarget(String name) {
        this.name = name;
    }

    public static String getName(){
        return "getName";
    }


    private String getName2(){
        return "getName2:"+name;
    }


    public String getName3(){
        return "getName3:"+name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MethodTarget{" +
                "name='" + name + '\'' +
                '}';
    }
}


class MethodTarget2 extends MethodTarget {

    @Override
    public String getName3(){
        return "child getName3";
    }
}