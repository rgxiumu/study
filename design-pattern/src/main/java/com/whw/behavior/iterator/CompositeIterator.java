package com.whw.behavior.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合迭代器模式
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/25
 */
public class CompositeIterator {

    public static void main(String[] args) {
        Composite composite1 = new ChildComposite();
        Composite composite2 = new ChildComposite();
        Composite composite3 = new ChildComposite();
        Composite composite4 = new ChildComposite();
        Composite composite5 = new FatherComposite();
        Composite composite6 = new FatherComposite();
        ((FatherComposite) composite5).addComposite(composite1);
        ((FatherComposite) composite5).addComposite(composite2);
        ((FatherComposite) composite5).addComposite(composite3);
        ((FatherComposite) composite6).addComposite(composite4);
        ((FatherComposite) composite5).addComposite(composite6);

        MyCompositeIterator myCompositeIterator = ((FatherComposite) composite5).getIterator();

        while(myCompositeIterator.hasNext()){
            myCompositeIterator.next();
        }


    }
}

interface Composite{

    void operation();
}

class ChildComposite implements Composite{

    @Override
    public void operation() {
        System.out.println("ChildComposite1");
    }
}


class FatherComposite implements Composite{

    private List<Composite> list = new ArrayList<>();

    public Composite getChild(int i){
        return list.get(i);
    }

    @Override
    public void operation() {

        System.out.println("FatherComposite2");
        list.forEach(Composite::operation);
    }

    void addComposite(Composite composite){
        list.add(composite);
    }

    void removeComposite(Composite composite){
        list.remove(composite);
    }


    MyCompositeIterator getIterator(){
        return new MyCompositeIteratorImpl(list);
    }


}


interface MyCompositeIterator{

    Composite next();

    Composite first();

    boolean hasNext();

}

class MyCompositeIteratorImpl implements MyCompositeIterator{

    private List<Composite> list;

    private int index = -1;

    public MyCompositeIteratorImpl(List<Composite> list) {
        this.list = list;
    }

    @Override
    public Composite next() {
        if(hasNext()){
            Composite composite = list.get(++index);
            composite.operation();
            return composite;
        }
        return null;
    }

    @Override
    public Composite first() {
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if(list.size() - index > 1){
            return true;
        }
        return false;
    }
}





