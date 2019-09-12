package pan.springbootkit.utils.designpattern.设计模式.行为型.访问者模式;

/**
 * Created by panzhangbao on 2017/9/22.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */

/** 访问者模式是对象的行为模式。访问者模式的目的是封装一些施加于某种数据结构元素之上的操作。
 * 一旦这些操作需要修改的话，接受这个操作的数据结构则可以保持不变。
 *
 */

/** 分派的概念

 　　变量被声明时的类型叫做变量的静态类型(Static Type)，有些人又把静态类型叫做明显类型(Apparent Type)；而变量所引用的对象的真实类型又叫做变量的实际类型(Actual Type)。比如：

 List list = null;
 list = new ArrayList();

 　　声明了一个变量list，它的静态类型（也叫明显类型）是List，而它的实际类型是ArrayList。

 　　根据对象的类型而对方法进行的选择，就是分派(Dispatch)，分派(Dispatch)又分为两种，即静态分派和动态分派。

 　　静态分派(Static Dispatch)发生在编译时期，分派根据静态类型信息发生。静态分派对于我们来说并不陌生，方法重载就是静态分派。

 　　动态分派(Dynamic Dispatch)发生在运行时期，动态分派动态地置换掉某个方法。
 　静态分派

 　　Java通过方法重载支持静态分派。用墨子骑马的故事作为例子，墨子可以骑白马或者黑马。
 */
public class Main23 {

    public static void main(String[] args) {
        Horse wh = new WhiteHorse();
        Horse bh = new BlackHorse();
        Mozi mozi = new Mozi();
        mozi.ride(wh);
        mozi.ride(bh);

        Horse h = new BlackHorse();
        h.eat();
    }
}
