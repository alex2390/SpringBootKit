package pan.springbootkit.utils.designpattern.设计模式.创建型.原型模式.简单形式;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class ConcretePrototype1 implements Prototype {

	@Override
    public Prototype clone(){
        // 最简单的克隆，新建一个自身对象，由于没有属性就不再复制值了
        Prototype prototype = new ConcretePrototype1();
        return prototype;
    }
}