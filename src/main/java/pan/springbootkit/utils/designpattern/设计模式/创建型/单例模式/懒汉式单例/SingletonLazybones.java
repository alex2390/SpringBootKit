package pan.springbootkit.utils.designpattern.设计模式.创建型.单例模式.懒汉式单例;

import lombok.NoArgsConstructor;

/**
 * 单例模式：懒汉式
 *
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
@NoArgsConstructor
public class SingletonLazybones {
	/** 上面的懒汉式单例类实现里对静态工厂方法使用了同步化，以处理多线程环境。
	 　　懒汉式其实是一种比较形象的称谓。既然懒，那么在创建对象实例的时候就不着急。会一直等到马上要使用对象实例的时候才会创建，懒人嘛，总是推脱不开的时候才会真正去执行工作，因此在装载对象的时候不创建对象实例。

	 private static SingletonLazybones instance = null;

	 　　懒汉式是典型的时间换空间,就是每次获取实例都会进行判断，看是否需要创建实例，浪费判断的时间。当然，如果一直没有人使用的话，那就不会创建实例，则节约内存空间

	 　　由于懒汉式的实现是线程安全的，这样会降低整个访问的速度，而且每次都要判断。
	 */


    private static SingletonLazybones instance = null;

    /**
     * 静态工厂方法
     */
    public static synchronized SingletonLazybones getInstance(){
        if(instance == null){
            instance = new SingletonLazybones();
        }
        return instance;
    }
}