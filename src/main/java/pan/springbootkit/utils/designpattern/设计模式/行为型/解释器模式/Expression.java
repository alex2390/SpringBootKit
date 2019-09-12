package pan.springbootkit.utils.designpattern.设计模式.行为型.解释器模式;

import javax.naming.NamingException;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public abstract class Expression {
    /**
     * 以环境为准，本方法解释给定的任何一个表达式
     * @param ctx
     */
    public abstract boolean interpret(Context2 ctx) throws NamingException;
    /**
     * 检验两个表达式在结构上是否相同
     */
    public abstract boolean equals(Object obj);
    /**
     * 返回表达式的hash code
     */
    public abstract int hashCode();
    /**
     * 将表达式转换成字符串
     */
    public abstract String toString();
}
