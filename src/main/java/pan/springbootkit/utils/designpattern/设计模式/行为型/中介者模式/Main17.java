package pan.springbootkit.utils.designpattern.设计模式.行为型.中介者模式;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */

/** 定义：用一个中介者对象封装一系列的对象交互，中介者使各对象不需要显示地相互作用，从而使耦合松散，而且可以独立地改变它们之间的交互。

 类型：行为类模式

 中介者模式的结构

 中介者模式又称为调停者模式，从类图中看，共分为3部分：

    抽象中介者：定义好同事类对象到中介者对象的接口，用于各个同事类之间的通信。一般包括一个或几个抽象的事件方法，并由子类去实现。
        中介者实现类：从抽象中介者继承而来，实现抽象中介者中定义的事件方法。从一个同事类接收消息，然后通过消息影响其他同时类。
    同事类：如果一个对象会影响其他的对象，同时也会被其他对象影响，那么这两个对象称为同事类。
        在类图中，同事类只有一个，这其实是现实的省略，在实际应用中，同事类一般由多个组成，他们之间相互影响，相互依赖。
        同事类越多，关系越复杂。并且，同事类也可以表现为继承了同一个抽象类的一组实现组成。在中介者模式中，同事类之间必须通过中介者才能进行消息传递。

 */
public class Main17 {

    public static void main(String[] args){

        AbstractColleague collA = new ColleagueA();
        AbstractColleague collB = new ColleagueB();

        System.out.println("==========设置A影响B==========");
        collA.setNumber(1288, collB);
        System.out.println("collA的number值："+collA.getNumber());
        System.out.println("collB的number值："+collB.getNumber());

        System.out.println("==========设置B影响A==========");
        collB.setNumber(87635, collA);
        System.out.println("collB的number值："+collB.getNumber());
        System.out.println("collA的number值："+collA.getNumber());
    }
}
