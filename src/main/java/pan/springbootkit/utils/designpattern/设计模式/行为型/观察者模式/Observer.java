package pan.springbootkit.utils.designpattern.设计模式.行为型.观察者模式;

/**
 * Created by panzhangbao on 2017-09-19.
 */
public interface Observer {
    /**
     * 更新接口
     * @param state    更新的状态
     */
    public void update(String state);
}