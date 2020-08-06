package com.whw.behavior.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式，提供一个对象顺序访问聚合类，而不暴露聚合类内部表示
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/25
 */
public class Iterator {

    public static void main(String[] args) {
        MyString myString = new MyStringImpl();
        myString.add("1111");
        myString.add("22222");
        myString.add("33333");
        MyIterator myIterator = myString.getIterator();

        while (myIterator.hasNext()){
            System.out.println(myIterator.next());
        }
    }
}

interface MyIterator{

    Object next();

    Object first();

    boolean hasNext();

}

class MyIteratorImpl implements MyIterator{

    private List<Object> list;

    private int index = -1;

    public MyIteratorImpl(List<Object> list) {
        this.list = list;
    }

    @Override
    public Object next() {
        if(hasNext()) {
            return list.get(++index);
        }
        return null;
    }

    @Override
    public Object first() {
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if(list.size()-index>1){
            return true;
        }
        return false;
    }
}

interface MyString{

    void add(Object myString);

    void remove(Object myString);

    MyIterator getIterator();
}

class MyStringImpl implements MyString{

    private List<Object> list = new ArrayList<>();

    @Override
    public void add(Object myString) {
        list.add(myString);
    }

    @Override
    public void remove(Object myString) {
        list.remove(myString);
    }

    @Override
    public MyIterator getIterator() {
        return (new MyIteratorImpl(list));
    }
}