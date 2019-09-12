package pan.springbootkit.utils.designpattern.设计模式.创建型.原型模式.简单形式;

/**
 * 原型模式：简单形式测试
 *
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class Main41 {
	/**
	 * 原型模式属于对象的创建模式。通过给出一个原型对象来指明所有创建的对象的类型，然后用复制这个原型对象的办法创建出更多同类型的对象。这就是选型模式的用意。
	 原型模式的结构

	 　　原型模式要求对象实现一个可以“克隆”自身的接口，这样就可以通过复制一个实例对象本身来创建一个新的实例。这样一来，通过原型实例创建新的对象，就不再需要关心这个实例本身的类型，只要实现了克隆自身的方法，就可以通过这个方法来获取新的对象，而无须再去通过new来创建。

	 　　原型模式有两种表现形式：（1）简单形式、（2）登记形式，这两种表现形式仅仅是原型模式的不同实现。
	 简单形式的原型模式

	 　　　　这种形式涉及到三个角色：

	 　　（1）客户(Client)角色：客户类提出创建对象的请求。

	 　　（2）抽象原型(Prototype)角色：这是一个抽象角色，通常由一个Java接口或Java抽象类实现。此角色给出所有的具体原型类所需的接口。

	 　　（3）具体原型（Concrete Prototype）角色：被复制的对象。此角色需要实现抽象的原型角色所要求的接口。
	 */
    public static void main(String[] args) {
        Client41 client41 = new Client41( new ConcretePrototype1() );
        client41.operation( new ConcretePrototype1() );
    }
}
