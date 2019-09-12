package pan.springbootkit.utils.designpattern.设计模式.结构型.适配器模式;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public interface Target {
    /**
     * 这是源类Adaptee也有的方法
     */
    public void sampleOperation1();
    /**
     * 这是源类Adapteee没有的方法
     */
    public void sampleOperation2();
}