package com.whw.behavior.state;

/**
 * 状态模式,程序运行时，出现多个状态选择，不适合if-else时，将状态抽取成状态类控制对象状态下逻辑处理
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/24
 */
public class State {

    public static void main(String[] args) {
        StateContext context = new StateContext("状态");
        context.excute();
        context.excute();
        context.excute();
        context.excute();
        context.excute();
    }

}

//上下文类
class StateContext{


    private String name;

    private IState state;

    public StateContext(String name) {
        this.name = name;
        state = new AState();
    }

    public void setState(IState state){
        this.state = state;
    }

    public void excute(){
        state.excute(this);
    }
}

//状态抽象类

abstract class IState{

    abstract void excute(StateContext stateContext);
}

class AState extends IState{

    @Override
    void excute(StateContext stateContext) {
        System.out.println("AState");
        stateContext.setState(new BState());
    }
}
class BState extends IState{

    @Override
    void excute(StateContext stateContext) {
        System.out.println("BState");
        stateContext.setState(new AState());
    }
}