package pan.springbootkit.utils.designpattern.设计模式.创建型.单例模式.饿汉式单例;

import lombok.NoArgsConstructor;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
@NoArgsConstructor
public class EagerSingleton {
	/**
	 * 饿汉式是典型的空间换时间，当类装载的时候就会创建类的实例，不管你用不用，先创建出来，
	 * 然后每次调用的时候，就不需要再判断，
	 * 节省了运行时间。
	 */



    private static EagerSingleton instance = new EagerSingleton();

    /**
     * 静态工厂方法
     */
    public static EagerSingleton getInstance(){
        return instance;
    }
}
