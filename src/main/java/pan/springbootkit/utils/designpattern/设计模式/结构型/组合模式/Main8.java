package pan.springbootkit.utils.designpattern.设计模式.结构型.组合模式;

/**
 * Created by panzhangbao on 2017/9/18.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */

/** 定义：“将对象组合成树形结构以表示‘部分-整体’的层次结构。
 *      组合模式使得用户对单个对象和组合对象的使用具有一致性。”
 */
public class Main8 {

    /** 就拿剪发办卡的事情来分析一下吧。

     首先，一张卡可以在总部，分店，加盟店使用，那么总部可以刷卡，分店也可以刷卡，加盟店也可以刷卡，这个属性结构的店面层级关系就明确啦。

     那么，总店刷卡消费与分店刷卡消费是一样的道理，那么总店与分店对会员卡的使用也具有一致性。
     */

    /**  那么我们就根据我们会员卡的消费，来模拟一下组合模式的实现吧！let's go！

     首先：

     1.我们的部件有，总店，分店，加盟店！

     2.我们的部件共有的行为是：刷会员卡

     3.部件之间的层次关系，也就是店面的层次关系是，总店下有分店、分店下可以拥有加盟店。

     有了我们这几个必要条件后，我的要求就是目前店面搞活动当我在总店刷卡后，就可以累积相当于在所有下级店面刷卡的积分总额
     */

    public static void main(String[] args) {
        MarketBranch rootBranch = new MarketBranch( "总店" );
        MarketBranch suzhouBranch = new MarketBranch( "苏州分店" );
        MarketJoin suzhouGusuquJoin = new MarketJoin( "苏州分店一：姑苏区" );
        MarketJoin suzhouXiangchengquJoin = new MarketJoin( "苏州分店一：相城区" );

        suzhouBranch.add(suzhouGusuquJoin);
        suzhouBranch.add( suzhouXiangchengquJoin );

        rootBranch.add( suzhouBranch );
        rootBranch.payByCard();
    }

}
