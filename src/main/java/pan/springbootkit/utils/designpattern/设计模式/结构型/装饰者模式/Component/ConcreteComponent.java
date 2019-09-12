package pan.springbootkit.utils.designpattern.设计模式.结构型.装饰者模式.Component;

/**
 * Created by panzhangbao on 2017/9/18.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class ConcreteComponent implements Component {

    @Override
    public void sampleOperation() {
        // 写相关的业务代码
        System.out.println("具体的 sampleOperation");
    }

}