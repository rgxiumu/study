package com.whw.structural.bridge;

/**
 * 桥接、适配器模式
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/23
 */
public class BridgeAndAdapter {


    public static void main(String[] args) {
        Size simeS = new SizeL();

        Colors colorr = new RedC();
        ColorTarget colorTarget = new ColorAdapter(colorr);

        TShirt tShirt = new SimpleShirt(colorTarget, simeS);

        tShirt.newShirt();
    }
}


interface Size{

    void size();
}

class SizeS implements Size{
    @Override
    public void size() {
        System.out.println("S");
    }
}

class SizeL implements Size{
    @Override
    public void size() {
        System.out.println("L");
    }
}

interface Colors{
    void color();
}

class RedC implements Colors{
    @Override
    public void color() {
        System.out.println("Red");
    }
}

class BlueC implements Colors{
    @Override
    public void color() {
        System.out.println("Blue");
    }
}

abstract class TShirt{
    protected ColorTarget colors;

    protected Size size;

    public TShirt(ColorTarget colors, Size size) {
        this.colors = colors;
        this.size = size;
    }

    abstract void newShirt();
}


class SimpleShirt extends TShirt{
    public SimpleShirt(ColorTarget colors, Size size) {
        super(colors, size);
    }

    @Override
    void newShirt() {
        System.out.println("Simple");
        colors.newColor();
        size.size();
    }
}


class BeautifulShirt extends TShirt{
    public BeautifulShirt(ColorTarget colors, Size size) {
        super(colors, size);
    }

    @Override
    void newShirt() {
        System.out.println("Beautiful");
        colors.newColor();
        size.size();
    }
}


interface ColorTarget {
    void newColor();
}

class ColorAdapter implements ColorTarget{

    private Colors colors;

    public ColorAdapter(Colors colors) {
        this.colors = colors;
    }

    @Override
    public void newColor() {
        System.out.println("throw adapter");
        colors.color();
    }
}