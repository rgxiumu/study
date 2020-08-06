package com.whw.behavior.state;

/**
 * 模拟线程状态切换
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/24
 */
public class ThreadStateDemo {


    public static void main(String[] args) {
        ThreadContext context = new ThreadContext();
        context.setName("show Time");
        context.startThread();
        context.run();
        context.stop();
    }
}

class ThreadContext{

    private String name;

    private ThreadState threadState;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ThreadContext() {
        threadState = new NewThread();
    }

    public void setThreadState(ThreadState threadState) {
        this.threadState = threadState;
    }

    public void startThread(){
        ((NewThread)threadState).startThread(this);
    }
    public void run(){
        ((StartThread)threadState).run(this);
    }
    public void stop(){
        ((RunThread)threadState).stop(this);
    }

}


abstract class ThreadState{

}

class NewThread extends ThreadState{

    public NewThread() {
        System.out.println("NewThread");
    }

    public void startThread(ThreadContext context) {
        System.out.println(context.getName() + " new --> start");
        context.setThreadState(new StartThread());
    }
}

class StartThread extends ThreadState{

    public StartThread() {
        System.out.println("StartThread");
    }

    public void run(ThreadContext context){
        System.out.println(context.getName() + " start --> run");

        context.setThreadState(new RunThread());
    }
}

class RunThread extends ThreadState{

    public RunThread() {
        System.out.println("RunThread");
    }

    public void stop(ThreadContext context){
        System.out.println(context.getName() + " run --> dead");
        context.setThreadState(new DeadThread());
    }
}

class DeadThread extends ThreadState{

    public DeadThread() {
        System.out.println("DeadThread");
    }

}