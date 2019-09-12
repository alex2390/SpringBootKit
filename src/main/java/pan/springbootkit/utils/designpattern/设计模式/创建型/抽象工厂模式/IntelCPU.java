package pan.springbootkit.utils.designpattern.设计模式.创建型.抽象工厂模式;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class IntelCPU implements CPU{

    // CPU 的针脚数
    private int pins = 0;

    public IntelCPU(int pins) {
        this.pins = pins;
    }

    @Override
    public void calculate() {
        System.out.println("Intel CUP 的针脚数：" + pins);
    }
}
