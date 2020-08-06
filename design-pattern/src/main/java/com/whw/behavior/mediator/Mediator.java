package com.whw.behavior.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介者模式，当交互的对象过多，网状结构浮渣时，使用中介者模式，使对象之间交互
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/24
 */
public class Mediator {

    public static void main(String[] args) {
        IMediator mediator = new MediatorImpl();
        Collage collageA = new CollageA();
        ((CollageA) collageA).setMediator(mediator);
        collageA.setName("AAAAA");
        Collage collageB = new CollageB();
        ((CollageB) collageB).setMediator(mediator);
        collageB.setName("BBBBB");
        mediator.register(collageA);
        mediator.register(collageB);

        collageA.send("testA");

        collageB.send("testB");

    }


}

interface IMediator{
    void register(Collage collage);
    void replay(Collage collage, String message);
}

class MediatorImpl implements IMediator{

    private List<Collage> collages = new ArrayList<>();

    @Override
    public void register(Collage collage) {
        collages.add(collage);
    }

    @Override
    public void replay(Collage collage, String message) {
        for (Collage collage1 : collages) {
            if(!collage1.getName().equalsIgnoreCase(collage.name)){
                collage1.receive(collage.getName(), message);
            }
        }
    }
}

abstract class Collage{

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract void send(String message);

    abstract void receive(String from, String message);

}

class CollageA extends Collage{


    private IMediator mediator;

    public void setMediator(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    void send(String message) {
        mediator.replay(this, message);
    }

    @Override
    void receive(String from, String message) {
        System.out.println("CollageA receive from:" + from + " message: " + message);
    }
}



class CollageB extends Collage{


    private IMediator mediator;

    public void setMediator(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    void send(String message) {
        mediator.replay(this, message);
    }

    @Override
    void receive(String from, String message) {
        System.out.println("CollageB receive from:" + from + " message: " + message);
    }
}
