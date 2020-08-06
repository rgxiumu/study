package com.whw.structural.bridge;

/**
 * 桥接模式
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/23
 */
public class Bridge {

    public static void main(String[] args) {
        Color red = new Red();

        Color black = new Black();

        AbstractPen redBigPen = new BigPan(red);
        redBigPen.usePen();
        AbstractPen blackBigPen = new BigPan(black);
        blackBigPen.usePen();

        AbstractPen redSmallPen = new SmallPen(red);
        redSmallPen.usePen();;

        AbstractPen blackSmallPen = new SmallPen(black);
        blackSmallPen.usePen();
    }

}

//实现化接口
interface Color{
    void paint(String pen);
}

class Red implements Color{
    @Override
    public void paint(String pen) {
        System.out.println("Red"+pen);
    }
}

class Black implements Color{
    @Override
    public void paint(String pen) {
        System.out.println("Black"+pen);
    }
}

//抽象化类
abstract class AbstractPen{
    protected Color color;

    protected AbstractPen(Color color) {
        this.color = color;
    }

    abstract void usePen();

}

class BigPan extends AbstractPen{

    protected BigPan(Color color) {
        super(color);
    }

    @Override
    void usePen() {
        color.paint("Big Pen");
    }
}

class SmallPen extends AbstractPen{

    protected SmallPen(Color color) {
        super(color);
    }

    @Override
    void usePen() {
        color.paint("Small Pen");
    }
}