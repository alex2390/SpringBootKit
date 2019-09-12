package pan.springbootkit.utils.designpattern.设计模式.创建型.原型模式.简单形式;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class Client41 {

    /**
     * 持有需要使用的原型接口对象
     */
    private Prototype prototype;
    /**
     * 构造方法，传入需要使用的原型接口对象
     */
    public Client41(Prototype prototype){
        this.prototype = prototype;
    }
    public void operation(Prototype example){
        //需要创建原型接口的对象
        Prototype copyPrototype = (Prototype) prototype.clone();
        System.out.println(copyPrototype.getClass().getName());
    }

}
