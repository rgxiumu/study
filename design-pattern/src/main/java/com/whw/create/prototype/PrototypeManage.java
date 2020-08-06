package com.whw.create.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * 原型管理器结构
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/22
 */
public class PrototypeManage {

    public static void main(String[] args) {
        AnimalManage animalManage = new AnimalManage();
        Animals animals = animalManage.get("bird");
        animals.display();
        Animals animals1 = animalManage.get("fish");
        animals1.display();
    }
}


interface Animals extends Cloneable {

    Object clone();

    void display();

}


class Birds implements Animals {

    @Override
    public Object clone() {
        Birds birds = null;
        try {
             birds = (Birds) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("copy failed");
        }
        return birds;
    }

    @Override
    public void display() {
        System.out.println("I can fly");
    }
}

class Fish implements Animals {
    @Override
    public Object clone() {
        Fish fish = null;
        try {
            fish = (Fish) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("copy failed");
        }
        return fish;
    }

    @Override
    public void display() {
        System.out.println("I can swimming");
    }
}


class AnimalManage {

    private Map<String, Animals> animalsMap = new HashMap<>();

    public AnimalManage() {
        animalsMap.put("bird", new Birds());
        animalsMap.put("fish", new Fish());
    }


    public Animals get(String key){
        Animals animals = animalsMap.get(key);
        return (Animals) animals.clone();
    }

    public void add(String key, Animals animals){
        animalsMap.put(key, animals);
    }
}