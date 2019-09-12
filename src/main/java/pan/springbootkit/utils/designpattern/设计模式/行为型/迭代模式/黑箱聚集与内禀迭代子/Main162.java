package pan.springbootkit.utils.designpattern.设计模式.行为型.迭代模式.黑箱聚集与内禀迭代子;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */

/** 黑箱聚集与内禀迭代子

 　　如果一个聚集的接口没有提供修改聚集元素的方法，这样的接口就是所谓的窄接口。

 　　聚集对象为迭代子对象提供一个宽接口，而为其他对象提供一个窄接口。换言之，聚集对象的内部结构应当对迭代子对象适当公开，
    以便迭代子对象能够对聚集对象有足够的了解，从而可以进行迭代操作。但是，聚集对象应当避免向其他的对象提供这些方法，
    因为其他对象应当经过迭代子对象进行这些工作，而不是直接操控聚集对象。

 　　在JAVA语言中，实现双重接口的办法就是将迭代子类设计成聚集类的内部成员类。这样迭代子对象将可以像聚集对象的内部成员一样访问聚集对象的内部结构。
    下面给出一个示意性的实现，说明这种双重接口的结构时怎么样产生的，以及使用了双重接口结构之后迭代子模式的实现方案。
    这种同时保证聚集对象的封装和迭代子功能的实现的方案叫做黑箱实现方案。

 　　由于迭代子是聚集的内部类，迭代子可以自由访问聚集的元素，所以迭代子可以自行实现迭代功能并控制对聚集元素的迭代逻辑。
    由于迭代子是在聚集的结构之内定义的，因此这样的迭代子又叫做内禀迭代子（Intrinsic Iterator）。
 */
public class Main162 {

    public static void main(String[] args) {
        Object[] objArray = {"One","Two","Three","Four","Five","Six"};
        //创建聚合对象
        Aggregate agg = new ConcreteAggregate(objArray);
        //循环输出聚合对象中的值
        Iterator it = agg.createIterator();
        while(!it.isDone()){
            System.out.println(it.currentItem());
            it.next();
        }
    }
}
