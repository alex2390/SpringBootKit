package pan.springbootkit.utils.designpattern.设计模式.创建型.建造模式.Demo1;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class ConcreteBuilder implements Builder {

    private Product product = new Product();

    /**
     * 产品零件构造方法
     */
    @Override
    public void buildPart1() {
        // 构建产品第一个零件
        product.setPart1( "编号：9257" );
    }

    @Override
    public void buildPart2() {
        // 构建第二个零件
        product.setPart2( "名称：xxx" );
    }

    @Override
    public Product retrieveResult() {
        return product;
    }
}
