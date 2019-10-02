package pan.springbootkit.utils.designpattern.设计模式.结构型.享元模式.复合享元模式;

import pan.springbootkit.utils.designpattern.设计模式.结构型.享元模式.单纯享元模式.Flyweight;

/**
 *
 *
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class ConcreteFlyweight implements Flyweight {
    private Character intrinsicState;

    /**
     * 构造函数，内蕴状态作为参数传入
     * @param state
     */
    public ConcreteFlyweight(Character state){
        this.intrinsicState = state;
    }


    /**
     * 外蕴状态作为参数传入方法中，改变方法的行为，
     * 但是并不改变对象的内蕴状态。
     */
    @Override
    public void operation(String state) {
		System.out.println("Intrinsic State = " + this.intrinsicState);
        System.out.println("Extrinsic State = " + state);
    }
}