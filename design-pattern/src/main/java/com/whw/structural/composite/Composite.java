package com.whw.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式，存在整体-局部树状结构关系,对对象有一致的访问性
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/23
 */
public class Composite {

    public static void main(String[] args) {
        FirstComposite f1 = new SonComposite();
        FirstComposite f2 = new SonComposite();
        FirstComposite f3 = new SonComposite();

        FirstComposite l1 = new LastComposite("1");
        FirstComposite l2 = new LastComposite("2");
        FirstComposite l3 = new LastComposite("3");
        FirstComposite l4 = new LastComposite("4");

        ((SonComposite) f1).add(l1);
        ((SonComposite) f1).add(f2);
        ((SonComposite) f2).add(l2);
        ((SonComposite) f2).add(l3);
        ((SonComposite) f2).add(f3);
        ((SonComposite) f3).add(l4);

        f1.display();
    }

}


interface FirstComposite{
    void display();
}

class SonComposite implements FirstComposite{

    private List<FirstComposite> child = new ArrayList<>();

    public void add(FirstComposite firstComposite){

        child.add(firstComposite);
    }

    public void remove(FirstComposite firstComposite){
        child.remove(firstComposite);
    }

    public List<FirstComposite> getChild(){
        return child;
    }

    @Override
    public void display() {
        child.forEach(FirstComposite :: display);
    }
}


class LastComposite implements FirstComposite{

    private String name;

    public LastComposite(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println(name);
    }
}