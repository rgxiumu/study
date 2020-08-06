package com.whw.behavior.momento;

/**
 * 备忘录模式，将当前对象的状态存储到备忘类中，通过备忘管理类统一管理
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/4/25
 */
public class Momento {

    public static void main(String[] args) {
        FaqiRen faqiRen = new FaqiRen();
        MemoryManage memoryManage = new MemoryManage();
        faqiRen.setName("1111");
        memoryManage.setMemory(faqiRen.createMemory());
        System.out.println(faqiRen.getName());
        faqiRen.setName("2222");
        System.out.println(faqiRen.getName());
        faqiRen.resore(memoryManage.getMemory());

        System.out.println(faqiRen.getName());
    }

}

class FaqiRen{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Memory createMemory(){
        return new Memory(name);
    }

    public void resore(Memory memory){
        name = memory.getName();
    }
}

class Memory{
    private String name;

    public Memory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class MemoryManage{

    private Memory memory;

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }
}
