package com.whw.behavior.command;

/**
 * 命令模式，在调用者和执行者之间建立命令类，通过对命令类的调用，间接执行
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/24
 */
public class Command {

    public static void main(String[] args) {
        Mingling minglingA = new MinglingA();
        Mingling minglingB = new MinglingB();

        Diaoyong diaoyongA = new Diaoyong(minglingA);
        diaoyongA.excute();

        Diaoyong diaoyongB = new Diaoyong(minglingB);
        diaoyongB.excute();
    }
}

class RunnerA{

    public void excute() {
        System.out.println("RunnerA");
    }
}

class RunnerB{

    public void excute() {
        System.out.println("RunnerB");
    }
}

//抽象命令类（Command）角色：声明执行命令的接口，拥有执行命令的抽象方法 execute()。
interface Mingling{

    void excute();
}

class MinglingA implements Mingling{
    private RunnerA runnerA = new RunnerA();
    @Override
    public void excute() {
        runnerA.excute();
    }
}
class MinglingB implements Mingling{
    private RunnerB runnerB = new RunnerB();
    @Override
    public void excute() {
        runnerB.excute();
    }
}

class Diaoyong{

    private Mingling mingling;

    public Diaoyong(Mingling mingling) {
        this.mingling = mingling;
    }

    public void excute(){
        mingling.excute();
    }
}