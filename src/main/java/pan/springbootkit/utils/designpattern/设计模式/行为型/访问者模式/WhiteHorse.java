package pan.springbootkit.utils.designpattern.设计模式.行为型.访问者模式;

/**
 * Created by panzhangbao on 2017/9/22.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class WhiteHorse extends Horse{

    @Override
    public void eat() {
        System.out.println("黑马吃草");
    }
}
