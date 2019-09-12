package pan.springbootkit.utils.designpattern.设计模式.创建型.建造模式.Demo2;

import java.util.Date;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public abstract class Builder2 {
    protected AutoMessage msg;

    //标题零件的建造方法
    public abstract void buildSubject();
    //内容零件的建造方法
    public abstract void buildBody();
    //收件人零件的建造方法
    public void buildTo(String to){
        msg.setTo(to);
    }
    //发件人零件的建造方法
    public void buildFrom(String from){
        msg.setFrom(from);
    }
    //发送时间零件的建造方法
    public void buildSendDate(){
        msg.setSendDate(new Date());
    }
    /**
     * 邮件产品完成后，用此方法发送邮件
     * 此方法相当于产品返还方法
     */
    public void sendMessage(){
        msg.send();
    }
}