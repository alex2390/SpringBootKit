package pan.springbootkit.utils.designpattern.设计模式.行为型.备忘录模式;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */

/** 　备忘录模式又叫做快照模式(Snapshot Pattern)或Token模式，是对象的行为模式。

 　　备忘录对象是一个用来存储另外一个对象内部状态的快照的对象。
 备忘录模式的用意是在不破坏封装的条件下，将一个对象的状态捕捉(Capture)住，并外部化，存储起来，
 从而可以在将来合适的时候把这个对象还原到存储起来的状态。备忘录模式常常与命令模式和迭代子模式一同使用。
 */

/** 备忘录模式的结构
 备忘录模式所涉及的角色有三个：备忘录(Memento)角色、发起人(Originator)角色、负责人(Caretaker)角色。
 */

/** 备忘录(Memento)角色

 　　备忘录角色又如下责任：

 　　（1）将发起人（Originator）对象的内战状态存储起来。备忘录可以根据发起人对象的判断来决定存储多少发起人（Originator）对象的内部状态。

 　　（2）备忘录可以保护其内容不被发起人（Originator）对象之外的任何对象所读取。

 　　备忘录有两个等效的接口：

 　　●　　窄接口：负责人（Caretaker）对象（和其他除发起人对象之外的任何对象）看到的是备忘录的窄接口(narrow interface)，
    这个窄接口只允许它把备忘录对象传给其他的对象。

 　　●　　宽接口：与负责人对象看到的窄接口相反的是，发起人对象可以看到一个宽接口(wide interface)，
    这个宽接口允许它读取所有的数据，以便根据这些数据恢复这个发起人对象的内部状态。
 */

/** 　发起人（Originator）角色

 　　发起人角色有如下责任：

 　　（1）创建一个含有当前的内部状态的备忘录对象。

 　　（2）使用备忘录对象存储其内部状态。
 */

/** 负责人（Caretaker）角色

 　　负责人角色有如下责任：

 　　（1）负责保存备忘录对象。

 　　（2）不检查备忘录对象的内容。
 */
public class Main18 {

    public static void main(String[] args) {

        Originator o = new Originator();
        Caretaker c = new Caretaker();
        //改变负责人对象的状态
        o.setState("On");
        //创建备忘录对象，并将发起人对象的状态储存起来
        c.saveMemento(o.createMemento());
        //修改发起人的状态
        o.setState("Off");
        //恢复发起人对象的状态
        o.restoreMemento(c.retrieveMemento());

        System.out.println(o.getState());
    }
}
