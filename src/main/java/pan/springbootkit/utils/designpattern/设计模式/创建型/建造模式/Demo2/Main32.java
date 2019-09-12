package pan.springbootkit.utils.designpattern.设计模式.创建型.建造模式.Demo2;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */


/** 使用场景

 　　假设有一个电子杂志系统，定期地向用户的电子邮件信箱发送电子杂志。用户可以通过网页订阅电子杂志，也可以通过网页结束订阅。当客户开始订阅时，系统发送一个电子邮件表示欢迎，当客户结束订阅时，系统发送一个电子邮件表示欢送。本例子就是这个系统负责发送“欢迎”和“欢送”邮件的模块。

 　　在本例中，产品类就是发给某个客户的“欢迎”和“欢送”邮件，如下图所示。

 　　虽然在这个例子里面各个产品类均有一个共同的接口，但这仅仅是本例子特有的，并不代表建造模式的特点。建造模式可以应用到具有完全不同接口的产品类上。大多数情况下是不知道最终构建出来的产品是什么样的，所以在标准的建造模式里面，一般是不需要对产品定义抽象接口的，因为最终构造的产品千差万别，给这些产品定义公共接口几乎是没有意义的。

 　　下图所示就是这个系统的类图。



 这个系统含有客户端（Client）、导演者（Director）、抽象建造者（Builder）、具体建造者（WelcomeBuilder和GoodbyeBuilder）、产品（WelcomeMessage和GoodbyeMessage）等角色。
 */

public class Main32 {

    public static void main(String[] args) {

        Builder2 builder = new WelcomeBuilder();
        Director2 director = new Director2(builder);
        director.construct("toAddress@126.com", "fromAddress@126.com");
    }
}
