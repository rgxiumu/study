package com.whw.behavior.strategy;

/**
 * 策略模式，程序中某个功能出现多种场景选择时，各个选择相对独立，封装内部执行方法
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/24
 */
public class Strategy {

    public static void main(String[] args) {
        NameContent nameContent = new NameContent(new ChiniseName());
        nameContent.showName();
        NameContent nameContent2 = new NameContent(new EnglishName());
        nameContent2.showName();
    }
}

interface ShowName{

    void showName();
}

class ChiniseName implements ShowName{
    @Override
    public void showName() {
        System.out.println("中国人");
    }
}

class EnglishName implements ShowName{
    @Override
    public void showName() {
        System.out.println("English");
    }
}


class NameContent{

    private ShowName showName;

    public NameContent(ShowName showName) {
        this.showName = showName;
    }

    public void showName(){
        showName.showName();
    }

}
