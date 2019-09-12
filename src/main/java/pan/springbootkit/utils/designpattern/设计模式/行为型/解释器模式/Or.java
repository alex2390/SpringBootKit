package pan.springbootkit.utils.designpattern.设计模式.行为型.解释器模式;

import javax.naming.NamingException;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class Or extends Expression {
    private Expression left,right;

    public Or(Expression left , Expression right){
        this.left = left;
        this.right = right;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Or)
        {
            return this.left.equals(((Or)obj).left) && this.right.equals(((Or)obj).right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean interpret(Context2 ctx) throws NamingException {
        return (left.interpret(ctx) || right.interpret(ctx));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " OR " + right.toString() + ")";
    }

}