package com.whw.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/23
 */
public class FlyWeight {


    public static void main(String[] args) {
        UnShare unShare1 = new UnShare("1", "1");
        UnShare unShare2 = new UnShare("2", "2");
        UnShare unShare3 = new UnShare("3", "3");
        UnShare unShare4 = new UnShare("4", "4");
        ShareFactory sf = new ShareFactory();
        Share share1 = sf.get("w");
        Share share2 = sf.get("b");
        Share share3 = sf.get("b");
        Share share4 = sf.get("w");
        Share share5 = sf.get("b");

        share1.display(unShare1);
        share2.display(unShare2);
        share3.display(unShare3);
        share4.display(unShare4);
        share5.display(unShare2);
    }
}


class UnShare{

    private String x;

    private String y;

    public UnShare(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}



interface Share{

    void display(UnShare unShare);
}

class BlackShare implements  Share{

    private String color;

    public BlackShare(String color) {
        this.color = color;
    }

    @Override
    public void display(UnShare unShare) {
        System.out.println(color+":x:"+unShare.getX()+"y:"+unShare.getY());
    }
}

class WhiteShare implements  Share{

    private String color;

    public WhiteShare(String color) {
        this.color = color;
    }

    @Override
    public void display(UnShare unShare) {
        System.out.println(color+":x:"+unShare.getX()+"y:"+unShare.getY());
    }
}


class ShareFactory {

    private Map<String, Share> shareMap = new HashMap<>();

    public Share get(String key){
        Share share = shareMap.get(key);
        if(null == share){
            if("b".equalsIgnoreCase(key)){
                share = new BlackShare("Black");
            }else if("w".equalsIgnoreCase(key)){
                share = new WhiteShare("White");
            }
            shareMap.put(key, share);
        }
        return share;
    }

}