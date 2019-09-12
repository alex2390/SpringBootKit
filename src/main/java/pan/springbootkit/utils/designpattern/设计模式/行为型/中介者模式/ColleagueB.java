package pan.springbootkit.utils.designpattern.设计模式.行为型.中介者模式;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
class ColleagueB extends AbstractColleague{

    public void setNumber(int number, AbstractColleague coll) {
        this.number = number;
        coll.setNumber(number/100);
    }
}