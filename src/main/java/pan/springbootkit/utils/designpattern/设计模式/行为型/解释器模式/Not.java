package pan.springbootkit.utils.designpattern.设计模式.行为型.解释器模式;

import javax.naming.NamingException;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class Not extends Expression {

    private Expression exp;

    public Not(Expression exp){
        this.exp = exp;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Not)
        {
            return exp.equals(
                    ((Not)obj).exp);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean interpret(Context2 ctx) throws NamingException {
        return !exp.interpret(ctx);
    }

    @Override
    public String toString() {
        return "(Not " + exp.toString() + ")";
    }

}