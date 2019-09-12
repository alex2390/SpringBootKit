package pan.springbootkit.utils.designpattern.设计模式.结构型.外观模式;

import pan.设计模式.结构型.外观模式.Module.ModuleA;
import pan.设计模式.结构型.外观模式.Module.ModuleB;
import pan.设计模式.结构型.外观模式.Module.ModuleC;

/**
 * Created by panzhangbao on 2017/9/18.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class Facade {

    //示意方法，满足客户端需要的功能
    public void test(){
        ModuleA a = new ModuleA();
        a.testA();
        ModuleB b = new ModuleB();
        b.testB();
        ModuleC c = new ModuleC();
        c.testC();
    }
}
