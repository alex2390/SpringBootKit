package pan.springbootkit.utils.designpattern.设计模式.行为型.中介者模式;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
abstract class AbstractColleague {
    protected int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number){
        this.number = number;
    }
    //抽象方法，修改数字时同时修改关联对象
    public abstract void setNumber(int number, AbstractColleague coll);
}