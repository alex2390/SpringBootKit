package pan.springbootkit.utils.designpattern.设计模式.结构型.享元模式.单纯享元模式;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class FlyweightFactory {
    private Map<Character, Flyweight> files = new HashMap<>();

    public Flyweight factory(Character state){
        //先从缓存中查找对象
        Flyweight fly = files.get(state);
        if(fly == null){
            //如果对象不存在则创建一个新的Flyweight对象
            fly = new ConcreteFlyweight(state);
            //把这个新的Flyweight对象添加到缓存中
            files.put(state, fly);
        }
        return fly;
    }
}