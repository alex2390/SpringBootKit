package pan.springbootkit.utils.designpattern.设计模式.结构型.桥接模式.AreaB;

import pan.设计模式.结构型.桥接模式.Bridge;

/**
 * Created by panzhangbao on 2017/9/18.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class AreaB1 implements Bridge {

    @Override
    public void targetAreaB() {
        System.out.println("我要去 B1");
    }
}
