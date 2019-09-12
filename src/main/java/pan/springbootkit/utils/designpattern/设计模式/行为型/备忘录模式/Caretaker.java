package pan.springbootkit.utils.designpattern.设计模式.行为型.备忘录模式;

/**
 * Created by panzhangbao on 2017/9/19.
 * Copyright © 2017年 panzhangbao. All rights reserved.
 */
public class Caretaker {

    private Memento memento;
    /**
     * 备忘录的取值方法
     */
    public Memento retrieveMemento(){
        return this.memento;
    }
    /**
     * 备忘录的赋值方法
     */
    public void saveMemento(Memento memento){
        this.memento = memento;
    }
}