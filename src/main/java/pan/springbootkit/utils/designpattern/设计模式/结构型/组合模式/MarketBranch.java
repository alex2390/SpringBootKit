package pan.springbootkit.utils.designpattern.设计模式.结构型.组合模式;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panzhangbao on 2017/9/18.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
// 分店 下面可以有加盟店
public class MarketBranch extends Market {

    // 加盟店列表
    List<Market> list = new ArrayList<Market>();

    public MarketBranch(String name) {
        this.name = name;
    }

    @Override
    public void add(Market m) {
        // TODO Auto-generated method stub
        list.add(m);
    }

    @Override
    public void remove(Market m) {
        // TODO Auto-generated method stub
        list.remove(m);
    }

    // 消费之后，该分店下的加盟店自动累加积分
    @Override
    public void payByCard() {
        // TODO Auto-generated method stub
        System.out.println(name + "消费,积分已累加入该会员卡");
        for (Market m : list) {
            m.payByCard();
        }
    }
}
