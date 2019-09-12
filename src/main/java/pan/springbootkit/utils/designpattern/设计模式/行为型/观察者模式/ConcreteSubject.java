package pan.springbootkit.utils.designpattern.设计模式.行为型.观察者模式;

/**
 * Created by panzhangbao on 2017-09-19.
 */
public class ConcreteSubject extends Subject{

    private String state;

    public String getState() {
        return state;
    }

    public void change(String newState){
        state = newState;
        System.out.println("主题状态为：" + state);
        //状态发生改变，通知各个观察者
        this.nodifyObservers(state);
    }
}