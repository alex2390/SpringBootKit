package pan.springbootkit.utils.designpattern.设计模式.结构型.装饰者模式;

/**
 * Created by panzhangbao on 2017/9/18.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */

/**
 * 装饰模式又名包装(Wrapper)模式。装饰模式以对客户端透明的方式扩展对象的功能，是继承关系的一个替代方案。
 装饰模式的结构

 　　装饰模式以对客户透明的方式动态地给一个对象附加上更多的责任。换言之，客户端并不会觉得对象在装饰前和装饰后有什么不同。
 装饰模式可以在不使用创造更多子类的情况下，将对象的功能加以扩展。
 */

import pan.springbootkit.utils.designpattern.设计模式.结构型.装饰者模式.Component.ConcreteComponent;
import pan.springbootkit.utils.designpattern.设计模式.结构型.装饰者模式.Decorator.ConcreteDecoratorA;
import pan.springbootkit.utils.designpattern.设计模式.结构型.装饰者模式.Decorator.Decorator;

/** 在装饰模式中的角色有：

 　　●　　抽象构件(Component)角色：给出一个抽象接口，以规范准备接收附加责任的对象。

 　　●　　具体构件(ConcreteComponent)角色：定义一个将要接收附加责任的类。

 　　●　　装饰(Decorator)角色：持有一个构件(Component)对象的实例，并定义一个与抽象构件接口一致的接口。

 　　●　　具体装饰(ConcreteDecorator)角色：负责给构件对象“贴上”附加的责任。
 */
public class Main10 {

    public static void main(String[] args) {
        Decorator decorator = new ConcreteDecoratorA( new ConcreteComponent() );
        decorator.sampleOperation();
    }

}
