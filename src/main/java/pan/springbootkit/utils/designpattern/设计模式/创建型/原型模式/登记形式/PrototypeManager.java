package pan.springbootkit.utils.designpattern.设计模式.创建型.原型模式.登记形式;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class PrototypeManager {
    /**
     * 用来记录原型的编号和原型实例的对应关系
     */
    private static Map<String,Prototype2> map = new HashMap<String,Prototype2>();
    /**
     * 私有化构造方法，避免外部创建实例
     */
    private PrototypeManager(){}
    /**
     * 向原型管理器里面添加或是修改某个原型注册
     * @param prototypeId 原型编号
     * @param prototype    原型实例
     */
    public synchronized static void setPrototype(String prototypeId , Prototype2 prototype){
        map.put(prototypeId, prototype);
    }
    /**
     * 从原型管理器里面删除某个原型注册
     * @param prototypeId 原型编号
     */
    public synchronized static void removePrototype(String prototypeId){
        map.remove(prototypeId);
    }
    /**
     * 获取某个原型编号对应的原型实例
     * @param prototypeId    原型编号
     * @return    原型编号对应的原型实例
     * @throws Exception    如果原型编号对应的实例不存在，则抛出异常
     */
    public synchronized static Prototype2 getPrototype(String prototypeId) throws Exception{
        Prototype2 prototype = map.get(prototypeId);
        if(prototype == null){
            throw new Exception("您希望获取的原型还没有注册或已被销毁");
        }
        return prototype;
    }
}