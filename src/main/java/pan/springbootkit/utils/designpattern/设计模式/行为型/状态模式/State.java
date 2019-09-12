package pan.springbootkit.utils.designpattern.设计模式.行为型.状态模式;

/**
 * Created by panzhangbao on 2017/9/22.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public interface State {

    /**
     * 状态对应的处理
     */
    public void handle(String sampleParameter);
}
