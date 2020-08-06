package com.whw.behavior.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * 解释器模式,定义语法解释
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/25
 */
public class Interpreter {

    public static void main(String[] args) {
        List<String> city = new ArrayList<>();
        city.add("1");
        city.add("2");
        city.add("3");

        List<String> person = new ArrayList<>();
        person.add("a");
        person.add("b");
        person.add("c");

        BaseInterpreter endInterpreter1 = new EndInterpreter(city);
        BaseInterpreter endInterpreter2 = new EndInterpreter(person);

        BaseInterpreter interpreter = new NoEndInterpreter(endInterpreter1, endInterpreter2);

        System.out.println(interpreter.interpreter("1&&a"));
        System.out.println(interpreter.interpreter("1&&c"));
        System.out.println(interpreter.interpreter("1&&d"));
    }

}


interface BaseInterpreter{
    boolean interpreter(String name);
}

class EndInterpreter implements BaseInterpreter{

    private List<String> list ;

    public EndInterpreter(List<String> list) {
        this.list = list;
    }

    @Override
    public boolean interpreter(String name) {
        if(list.contains(name)){
            return true;
        }
        return false;
    }
}

class NoEndInterpreter implements BaseInterpreter{

    private BaseInterpreter end1;
    private BaseInterpreter end2;

    public NoEndInterpreter(BaseInterpreter end1, BaseInterpreter end2) {
        this.end1 = end1;
        this.end2 = end2;
    }

    @Override
    public boolean interpreter(String name) {
        String[] strings = name.split("&&");
        if(end1.interpreter(strings[0]) && end2.interpreter(strings[1])){
            return true;
        }
        return false;
    }
}

