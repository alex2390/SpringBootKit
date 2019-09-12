package pan.springbootkit.utils.designpattern.设计模式.创建型.原型模式.简单形式;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public interface Prototype{
    /**
     * 克隆自身的方法
     * @return 一个从自身克隆出来的对象
     */
    Object clone();
}