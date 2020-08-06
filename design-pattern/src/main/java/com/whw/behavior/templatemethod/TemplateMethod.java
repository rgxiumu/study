package com.whw.behavior.templatemethod;

/**
 * 模板方法模式，类结构模式
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/24
 */
public class TemplateMethod {

    public static void main(String[] args) {
        ClassDaily classDaily = new WorkDay();
        classDaily.daily();


        ClassDaily classDaily1 = new FestivalDay();
        classDaily1.daily();
    }

}

abstract class ClassDaily {

    private void method1(){
        System.out.println("起床吃饭");
    }

    private void method2(){
        System.out.println("上床睡觉");
    }

    private void method4(){
        System.out.println("工作中...");
    }


    abstract void method3();

    boolean isFestival(){
        return false;
    }

    void daily(){
        method1();
        if(isFestival()){
            method3();
        }else {
            method4();
        }
        method2();
    }
}


class WorkDay extends ClassDaily{

    @Override
    void method3() {
        System.out.println("工作中...");
    }

    @Override
    boolean isFestival() {
        return false;
    }
}


class FestivalDay extends  ClassDaily{
    @Override
    void method3() {
        System.out.println("放假中...");
    }

    @Override
    boolean isFestival() {
        return true;
    }
}
