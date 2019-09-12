package pan.springbootkit.utils.designpattern.设计模式.结构型.装饰者模式.Decorator;

import pan.设计模式.结构型.装饰者模式.Component.Component;

/**
 * Created by panzhangbao on 2017/9/18.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class Decorator implements Component {
    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void sampleOperation() {
        // 委派给构件
        component.sampleOperation();
    }

}
