package pan.springbootkit.utils.designpattern.设计模式.结构型.装饰者模式.Decorator;

import pan.springbootkit.utils.designpattern.设计模式.结构型.装饰者模式.Component.Component;

/**
 * Created by panzhangbao on 2017/9/18.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void sampleOperation() {
        super.sampleOperation();
        // 写相关的业务代码
        System.out.println("装饰器 A");
    }
}
