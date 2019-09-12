package pan.springbootkit.utils.designpattern.设计模式.行为型.状态模式;

/**
 * Created by panzhangbao on 2017/9/22.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class ConcreteStateB implements State{

    @Override
    public void handle(String sampleParameter) {

        System.out.println("ConcreteStateB handle ：" + sampleParameter);
    }
}
