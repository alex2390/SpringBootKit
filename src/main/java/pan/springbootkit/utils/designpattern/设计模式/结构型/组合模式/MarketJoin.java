package pan.springbootkit.utils.designpattern.设计模式.结构型.组合模式;

/**
 * Created by panzhangbao on 2017/9/18.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
// 加盟店 下面不在有分店和加盟店，最底层
public class MarketJoin extends Market {

    public MarketJoin(String name) {
        this.name = name;
    }

    @Override
    public void add(Market m) {
	}

    @Override
    public void remove(Market m) {
	}

    @Override
    public void payByCard() {
System.out.println(name + "消费,积分已累加入该会员卡");
    }
	}