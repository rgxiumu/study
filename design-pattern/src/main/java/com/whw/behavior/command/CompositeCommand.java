package com.whw.behavior.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合命令模式， 调用者也是命令对象
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/24
 */
public class CompositeCommand {


    public static void main(String[] args) {
        CommandCInvoker commandCInvoker = new CommandCInvoker();
        commandCInvoker.add(new CommandA());
        commandCInvoker.add(new CommandB());
        commandCInvoker.add(new CommandA());
        commandCInvoker.excute();
    }
}

class RecevierA {

    void excute(){
        System.out.println("RecevierA");
    }
}
class RecevierB {

    void excute(){
        System.out.println("RecevierB");
    }
}

interface ICommand{

    void excute();
}

class CommandA implements ICommand{
    private RecevierA recevierA = new RecevierA();
    @Override
    public void excute() {
        recevierA.excute();
    }
}
class CommandB implements ICommand{
    private RecevierB commandB = new RecevierB();

    @Override
    public void excute() {
        commandB.excute();
    }
}


class CommandCInvoker implements ICommand{

    private List<ICommand> list = new ArrayList<>();

    public void add(ICommand command){
        list.add(command);
    }

    @Override
    public void excute() {
        list.forEach(ICommand::excute);
    }
}