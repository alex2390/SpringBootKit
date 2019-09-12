package pan.springbootkit.utils.designpattern.设计模式.行为型.解释器模式;

import javax.naming.Name;
import javax.naming.NamingException;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class Variable extends Expression {

    private String name;

    public Variable(String name){
        this.name = name;
    }
    @Override
    public boolean equals(Object obj) {

        if(obj != null && obj instanceof Variable)
        {
            return this.name.equals(
                    ((Variable)obj).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean interpret(Context2 ctx) throws NamingException {
        return (boolean)ctx.lookup(  this );
    }

}
