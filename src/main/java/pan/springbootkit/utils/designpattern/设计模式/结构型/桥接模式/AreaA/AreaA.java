package pan.springbootkit.utils.designpattern.设计模式.结构型.桥接模式.AreaA;

import pan.设计模式.结构型.桥接模式.Bridge;

/**
 * Created by panzhangbao on 2017/9/18.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public abstract class AreaA {

    // 引用桥接口
    public Bridge bridge;

    // 来源地
    public abstract void fromAreaA();
}
