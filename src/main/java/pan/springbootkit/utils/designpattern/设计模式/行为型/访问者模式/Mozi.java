package pan.springbootkit.utils.designpattern.设计模式.行为型.访问者模式;

/**
 * Created by panzhangbao on 2017/9/22.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class Mozi {
    public void ride(Horse h){
        System.out.println("骑马");
    }

    public void ride(WhiteHorse wh){
        System.out.println("骑白马");
    }

    public void ride(BlackHorse bh){
        System.out.println("骑黑马");
    }
}
