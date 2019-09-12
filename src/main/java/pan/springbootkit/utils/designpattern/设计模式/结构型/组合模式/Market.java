package pan.springbootkit.utils.designpattern.设计模式.结构型.组合模式;

/**
 * Created by panzhangbao on 2017/9/18.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public abstract class Market {

    public String name;

    public abstract void add(Market m);

    public abstract void remove(Market m);

    public abstract void payByCard();
}
