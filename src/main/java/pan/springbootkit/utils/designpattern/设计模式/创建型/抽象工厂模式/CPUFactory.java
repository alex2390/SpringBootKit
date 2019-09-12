package pan.springbootkit.utils.designpattern.设计模式.创建型.抽象工厂模式;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class CPUFactory {

    public static CPU createCPU(int type) {
        CPU cpu = null;
        if (type == 1) {
            cpu = new IntelCPU( 755 );
        }else if(type == 2) {
            cpu = new AmdCPU( 938 );
        }
        return cpu;
    }

}
