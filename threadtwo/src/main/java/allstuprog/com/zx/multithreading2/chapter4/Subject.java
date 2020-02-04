package allstuprog.com.zx.multithreading2.chapter4;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    //为观察者通知的数据
    private int state = 0;
    //保存所有观察者
    private List<Observer> observers = new ArrayList<>();
    //为数据赋值，并通知所有观察者
    public void setState(int state) {
        if(state == this.state) {
            return;
        }
        this.state = state;
        notifyAllObservers();
    }
    //增加观察者
    public void attach(Observer observer) {
        observers.add(observer);
    }
    //获取数据
    public int getState() {
        return this.state;
    }
    //通知所有观察者
    private void notifyAllObservers() {
        observers.forEach(Observer::update);
    }

}
