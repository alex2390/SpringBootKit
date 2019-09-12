package pan.springbootkit.utils.designpattern.设计模式.创建型.建造模式.Demo1;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public interface Builder {
	void buildPart1();

    void buildPart2();

    Product retrieveResult();
}
