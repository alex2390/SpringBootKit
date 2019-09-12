package pan.springbootkit.utils.designpattern.设计模式.行为型.责任链模式;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class ConcreteHandler extends Handler {
    /**
     * 处理方法，调用此方法处理请求
     */
    @Override
    public void handleRequest() {
        /**
         * 判断是否有后继的责任对象
         * 如果有，就转发请求给后继的责任对象
         * 如果没有，则处理请求
         */
        if(getSuccessor() != null)
        {
            System.out.println("放过请求");
            getSuccessor().handleRequest();
        }else
        {
            System.out.println("处理请求");
        }
    }

}
