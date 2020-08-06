package com.whw.behavior.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式，将元素与元素操作拆分开来，针对不同的访问者，执行不同的操作
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/25
 */
public class Visitor {

    public static void main(String[] args) {
        IVisitor visitor1 = new VisitorA();
        IVisitor visitor2 = new VisitorB();
        Element element1 = new ElementA();
        Element element2 = new ElementB();
//        element1.accept(visitor1);
//        element1.accept(visitor2);

        VisitorManager visitorManager = new VisitorManager();
        visitorManager.add(element1);
        visitorManager.add(element2);
        visitorManager.accept(visitor1);
        System.out.println("-----------");
        visitorManager.accept(visitor2);
    }
}


interface Element{

    void accept(IVisitor visitor);

    String message();

}

interface IVisitor{
    void visit(ElementA elementA);
    void visit(ElementB elementB);
}

class ElementA implements Element{
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String message() {
        return "ElementA";
    }
}
class ElementB implements Element{
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String message() {
        return "ElementB";
    }
}

class VisitorA implements IVisitor{
    @Override
    public void visit(ElementA elementA) {
        System.out.println("VisitorA visit -->"+ elementA.message());
    }

    @Override
    public void visit(ElementB elementB) {
        System.out.println("VisitorA visit -->"+ elementB.message());
    }
}


class VisitorB implements IVisitor{
    @Override
    public void visit(ElementA elementA) {
        System.out.println("VisitorB visit -->"+ elementA.message());
    }

    @Override
    public void visit(ElementB elementB) {
        System.out.println("VisitorB visit -->"+ elementB.message());
    }
}


class VisitorManager{

    private List<Element> list = new ArrayList<>();

    void add(Element element){
        list.add(element);
    }

    void accept(IVisitor visitor){

        list.forEach(element -> element.accept(visitor));
    }

}