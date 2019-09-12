package pan.springbootkit.utils.designpattern.设计模式.创建型.单例模式;

import pan.springbootkit.utils.designpattern.设计模式.创建型.单例模式.懒汉式单例.SingletonLazybones;
import pan.springbootkit.utils.designpattern.设计模式.创建型.单例模式.饿汉式单例.EagerSingleton;

/**
 * 单例测试
 *
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class Main5 {

	/**
	 * 作为对象的创建模式，单例模式确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。这个类称为单例类。
	 *	单例模式的结构
	 *	单例模式的特点：
	 *		单例类只能有一个实例。
	 *		单例类必须自己创建自己的唯一实例。
	 *		单例类必须给所有其他对象提供这一实例。
	 */

    public static void main(String[] args) {
        // 获取饿汉单例
        EagerSingleton eagerSingleton = EagerSingleton.getInstance();

        // 懒汉单例
        SingletonLazybones lazySingleton = SingletonLazybones.getInstance();
    }
}
