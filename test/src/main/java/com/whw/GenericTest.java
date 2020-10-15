package com.whw;

import java.util.List;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2020/9/30
 */
public class GenericTest {


    public static void test1(List<? extends Number> list){
//        list.add(1);
        /**
         * 1 .  List<? extends Number>  list  就是对加入的元素进行上限限制，，，只要是Number或者Number的子类都事可以的，，，但具体是哪一个？  大哥， 这谁知道啊，，，当然编译器也是不知道的，。。。。那编译器都不知道是什么类型，你就直接add一个Integer类型的，，，要是人家后面确定是Float类型的，那不就是搞错了吗？  这样的原因，导致java编译器直接就不让add了，从而避免了该问题的发生。
         * 2. 还有种解释是，，，现在JAVA的强类型检测，当编辑器遇到<? extends Number>时候，他只知道有东西要加入了，至于具体是什么类型，就不得而知了，所以在声明该变量的时候，只是存了一个变量加入类型的标记符，也就是占位符，具体是什么类型，还不能确定。。。当list调用add函数加入的时候， 会进行类型判断的，这个时候，因为上面保存的只是占位符，却没有真正的标记变量的类型。。。导致没法判断，就这样JAVAl数据类型的强类型问题问题，就会导致报错和各种问题，所以JAVA就直接不让add了，从而避免了该问题的发生。
         */
    }
    public static void test2(List<? super Number> list){
        list.add(1);
        /**
         * 首先先说明下这里色List<？ super Number> list，，，这个就是泛型的下线限制符。。。里面可以存储的对象是Number类以及Number类的父类。。。至于具体是哪个类型，就不得而知了。这就更奇怪了，为啥Integer类存储成功了，而Object类存储报错了。。。这是为啥呢？
         *         首先先说，为啥add Integer对象成功了。。。其实是这样的，因为JAVA的继承原理，对应的属性图都是从上到下的方式，Integer对象肯定是Number对象。。。而List<？ super Number> list可以存储的就是Number类或者Number类的父类，但具体是哪个类型，就不得而知了。。。当然编译器也是不知道的。。。但就是因为JAVA的继承原理，让每一个Number子类都同时是Number类，，，同时也是所以Number父类的子类。。。这不就是完全符合了List<? super Number>list 可以存储的要求了吗？  因为Number子类，全部可以看成是Number类，然后Number类都是可以符合该List要求的。。。。那这样明显就可以存储成功了啊。。。
         *         然后在说add Object失败的问题，很明显，在申明一次，List<？ super Number> list存储的只能是Number类或者Number类的父类，但具体是哪个类？？？  谁知道啊，，，有谁知道吗？？？ 鬼都不知道，编辑器当然也是不知道的
         * 。。。这个时候，你add object对象，编辑器都不知道里面存储的数据类型，因为JAVA的强类型和Java类型检测机制，，，这里就会报错啦。。。。
         */
    }
}
