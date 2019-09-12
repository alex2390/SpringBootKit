package pan.springbootkit.utils.designpattern.设计模式.创建型.建造模式.Demo2;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class WelcomeBuilder extends Builder2 {
    public WelcomeBuilder(){
        msg = new WelcomeMessage();
    }
    @Override
    public void buildBody() {
        msg.setBody("欢迎内容");
    }

    @Override
    public void buildSubject() {
        msg.setSubject("欢迎标题");
    }

}
