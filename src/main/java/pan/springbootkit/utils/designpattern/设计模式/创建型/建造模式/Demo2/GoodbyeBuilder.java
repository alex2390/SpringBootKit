package pan.springbootkit.utils.designpattern.设计模式.创建型.建造模式.Demo2;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class GoodbyeBuilder extends Builder2 {

    public GoodbyeBuilder(){
        msg = new GoodbyeMessage();
    }
    @Override
    public void buildBody() {
		msg.setBody("欢送内容");
    }

    @Override
    public void buildSubject() {
msg.setSubject("欢送标题");
    }

}