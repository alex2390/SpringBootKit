package pan.springbootkit.utils.designpattern.设计模式.行为型.迭代模式.白箱聚集与外禀迭代子;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */

/** 白箱聚集与外禀迭代子

 　　如果一个聚集的接口提供了可以用来修改聚集元素的方法，这个接口就是所谓的宽接口。

 　　如果聚集对象为所有对象提供同一个接口，也就是宽接口的话，当然会满足迭代子模式对迭代子对象的要求。
    但是，这样会破坏对聚集对象的封装。这种提供宽接口的聚集叫做白箱聚集。聚集对象向外界提供同样的宽接口，如下图所示：

 　　由于聚集自己实现迭代逻辑，并向外部提供适当的接口，使得迭代子可以从外部控制聚集元素的迭代过程。
    这样一来迭代子所控制的仅仅是一个游标而已，这种迭代子叫做游标迭代子（Cursor Iterator）。
    由于迭代子是在聚集结构之外的，因此这样的迭代子又叫做外禀迭代子（Extrinsic Iterator）。

 　　现在看一看白箱聚集与外禀迭代子的实现。一个白箱聚集向外界提供访问自己内部元素的接口（称作遍历方法或者Traversing Method），
    从而使外禀迭代子可以通过聚集的遍历方法实现迭代功能。

 　　因为迭代的逻辑是由聚集对象本身提供的，所以这样的外禀迭代子角色往往仅仅保持迭代的游标位置。

 　　一个典型的由白箱聚集与外禀迭代子组成的系统如下图所示，在这个实现中具体迭代子角色是一个外部类，而具体聚集角色向外界提供遍历聚集元素的接口。
 */

/** 迭代子模式涉及到以下几个角色：

 　　●　　抽象迭代子(Iterator)角色：此抽象角色定义出遍历元素所需的接口。

 　　●　　具体迭代子(ConcreteIterator)角色：此角色实现了Iterator接口，并保持迭代过程中的游标位置。

 　　●　　聚集(Aggregate)角色：此抽象角色给出创建迭代子(Iterator)对象的接口。

 　　●　　具体聚集(ConcreteAggregate)角色：实现了创建迭代子(Iterator)对象的接口，返回一个合适的具体迭代子实例。

 　　●　　客户端(Client)角色：持有对聚集及其迭代子对象的引用，调用迭代子对象的迭代接口，也有可能通过迭代子操作聚集元素的增加和删除。
 */
public class Main161 {

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
