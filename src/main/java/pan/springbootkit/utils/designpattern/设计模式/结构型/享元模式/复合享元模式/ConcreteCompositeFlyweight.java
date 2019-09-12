package pan.springbootkit.utils.designpattern.设计模式.结构型.享元模式.复合享元模式;

import pan.springbootkit.utils.designpattern.设计模式.结构型.享元模式.单纯享元模式.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class ConcreteCompositeFlyweight implements Flyweight {

    private Map<Character,Flyweight> files = new HashMap<Character,Flyweight>();
    /**
     * 增加一个新的单纯享元对象到聚集中
     */
    public void add(Character key , Flyweight fly){
        files.put(key,fly);
    }
    /**
     * 外蕴状态作为参数传入到方法中
     */
    @Override
    public void operation(String state) {
        Flyweight fly = null;
        for(Object o : files.keySet()){
            fly = files.get(o);
            fly.operation(state);
        }

    }
}
