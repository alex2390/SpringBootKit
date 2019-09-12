package pan.springbootkit.utils.designpattern.设计模式.创建型.建造模式.Demo2;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class Director2 {
    Builder2 builder;
    /**
     * 构造子
     */
    public Director2(Builder2 builder){
        this.builder = builder;
    }
    /**
     * 产品构造方法，负责调用各零件的建造方法
     */
    public void construct(String toAddress , String fromAddress){
        this.builder.buildTo(toAddress);
        this.builder.buildFrom(fromAddress);
        this.builder.buildSubject();
        this.builder.buildBody();
        this.builder.buildSendDate();
        this.builder.sendMessage();
    }
}
