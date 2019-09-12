package pan.springbootkit.utils.designpattern.设计模式.结构型.代理模式;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class ProxyObject extends AbstractObject{

    RealObject realObject = new RealObject();

    @Override
    public void operation() {
        //调用目标对象之前可以做相关操作
        System.out.println("before");

        realObject.operation();

        //调用目标对象之后可以做相关操作
        System.out.println("after");
    }
}