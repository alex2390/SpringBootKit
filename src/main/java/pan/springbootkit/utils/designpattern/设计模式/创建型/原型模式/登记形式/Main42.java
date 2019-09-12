package pan.springbootkit.utils.designpattern.设计模式.创建型.原型模式.登记形式;

/**
 * Created by panzhangbao on 2017/9/14.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */

/** 登记形式的原型模式
 *  作为原型模式的第二种形式，它多了一个原型管理器(PrototypeManager)角色，该角色的作用是：创建具体原型类的对象，并记录每一个被创建的对象。
 */

/** 两种形式的比较

 　　简单形式和登记形式的原型模式各有其长处和短处。

 　　如果需要创建的原型对象数目较少而且比较固定的话，可以采取第一种形式。在这种情况下，原型对象的引用可以由客户端自己保存。

 　　如果要创建的原型对象数目不固定的话，可以采取第二种形式。在这种情况下，客户端不保存对原型对象的引用，这个任务被交给管理员对象。在复制一个原型对象之前，客户端可以查看管理员对象是否已经有一个满足要求的原型对象。如果有，可以直接从管理员类取得这个对象引用；如果没有，客户端就需要自行复制此原型对象。
 */
public class Main42 {

     public static void main(String[] args) {
         try{
             Prototype2 p1 = new ConcretePrototype21();
             PrototypeManager.setPrototype("p1", p1);
             //获取原型来创建对象
             Prototype2 p3 = PrototypeManager.getPrototype("p1").clone();
             p3.setName("张三");
             System.out.println("第一个实例：" + p3);
             //有人动态的切换了实现
             Prototype2 p2 = new ConcretePrototype22();
             PrototypeManager.setPrototype("p1", p2);
             //重新获取原型来创建对象
             Prototype2 p4 = PrototypeManager.getPrototype("p1").clone();
             p4.setName("李四");
             System.out.println("第二个实例：" + p4);
             //有人注销了这个原型
             PrototypeManager.removePrototype("p1");
             //再次获取原型来创建对象
             Prototype2 p5 = PrototypeManager.getPrototype("p1").clone();
             p5.setName("王五");
             System.out.println("第三个实例：" + p5);
         }catch(Exception e){
             e.printStackTrace();
         }
     }
}
