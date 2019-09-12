package pan.springbootkit.utils.designpattern.设计模式.创建型.原型模式.登记形式;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public interface Prototype2{
    Prototype2 clone();
    String getName();
    void setName(String name);
}