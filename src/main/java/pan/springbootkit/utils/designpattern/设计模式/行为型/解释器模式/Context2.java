package pan.springbootkit.utils.designpattern.设计模式.行为型.解释器模式;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class Context2 {

    private Map<Variable,Boolean> map = new HashMap<Variable,Boolean>();

    public void assign(Variable var , boolean value){
        map.put(var, new Boolean(value));
    }

    public boolean lookup(Variable var) throws IllegalArgumentException{
        Boolean value = map.get(var);
        if(value == null){
            throw new IllegalArgumentException();
        }
        return value.booleanValue();
    }
}
