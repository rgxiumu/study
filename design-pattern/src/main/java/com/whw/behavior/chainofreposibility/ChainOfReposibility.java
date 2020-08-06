package com.whw.behavior.chainofreposibility;

/**
 * 责任链模式，提交的请求，有多个处理对象，在不明确处理对象时，用责任链的方式把请求向下传递
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/24
 */
public class ChainOfReposibility {

    public static void main(String[] args) {
        Handler handlerA = new HandlerA();
        Handler handlerB = new HandlerB();
        handlerA.setNext(handlerB);
        handlerA.excute(3);
        handlerA.excute(30);
        handlerA.excute(302);
    }
}


abstract class Handler{

    Handler next;

    void setNext(Handler handler){
        this.next = handler;
    }

    abstract void excute(int day);

}

class HandlerA extends Handler{

    @Override
    void excute(int day) {
        if(day < 10){
            System.out.println(day+"天，HandlerA处理结束");
        }else {
            if(null != next){
                next.excute(day);
            }
            else {
                System.out.println(day+"天，HandlerA处理不能！！！ERROR");
            }
        }
    }
}

class HandlerB extends Handler{

    @Override
    void excute(int day) {
        if(day >= 10 && day < 100){
            System.out.println(day+"天，HandlerB处理结束");
        }else {
            if(null != next){
                next.excute(day);
            }
            else {
                System.out.println(day+"天，HandlerB处理不能！！！ERROR");
            }
        }
    }
}