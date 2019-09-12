package pan.springbootkit.utils.designpattern.设计模式.行为型.模板方法模式;

/**
 * Created by panzhangbao on 2017/9/22.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class ConcreteTemplate extends AbstractTemplate{

    //基本方法的实现
    @Override
    public void abstractMethod() {
        //业务相关的代码
    }
    //重写父类的方法
    @Override
    public void hookMethod() {
        //业务相关的代码
    }
}
