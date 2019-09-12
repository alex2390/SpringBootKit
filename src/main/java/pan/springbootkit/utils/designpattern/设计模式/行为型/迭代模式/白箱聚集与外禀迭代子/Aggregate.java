package pan.springbootkit.utils.designpattern.设计模式.行为型.迭代模式.白箱聚集与外禀迭代子;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public abstract class Aggregate {
    /**
     * 工厂方法，创建相应迭代子对象的接口
     */
    public abstract ConcreteIterator createIterator();
}