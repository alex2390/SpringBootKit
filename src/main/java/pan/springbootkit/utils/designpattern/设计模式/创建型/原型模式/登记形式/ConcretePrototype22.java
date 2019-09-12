package pan.springbootkit.utils.designpattern.设计模式.创建型.原型模式.登记形式;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class ConcretePrototype22 implements Prototype2 {
    private String name;

    @Override
    public Prototype2 clone(){
        ConcretePrototype22 prototype = new ConcretePrototype22();
        prototype.setName(this.name);
        return prototype;
    }

    @Override
    public String toString(){
        return "Now in Prototype1 , name = " + this.name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}