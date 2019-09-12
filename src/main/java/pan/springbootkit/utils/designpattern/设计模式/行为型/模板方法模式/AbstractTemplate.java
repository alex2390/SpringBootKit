package pan.springbootkit.utils.designpattern.设计模式.行为型.模板方法模式;

/**
 * Created by panzhangbao on 2017/9/22.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public abstract class AbstractTemplate {

    /**
     * 模板方法
     */
    public void templateMethod(){
        //调用基本方法
        abstractMethod();
        hookMethod();
        concreteMethod();
    }
    /**
     * 基本方法的声明（由子类实现）
     */
    protected abstract void abstractMethod();
    /**
     * 基本方法(空方法)
     */
    protected void hookMethod(){}
    /**
     * 基本方法（已经实现）
     */
    private final void concreteMethod(){
        //业务相关的代码
    }
}
