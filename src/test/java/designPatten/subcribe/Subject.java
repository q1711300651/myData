package designPatten.subcribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ww on 17/3/4.
 */
public class Subject implements ISubject {
    List<IObserver>observers=new ArrayList<>();

    public List<IObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<IObserver> observers) {
        this.observers = observers;
    }

    @Override
    public void attachObserver(IObserver observer) {
    observers.add(observer);
    }

    @Override
    public void detachObserver(IObserver observer) {
    observers.remove(observer);
    }

    @Override
    public void notifyObservers(String msg) {
        for (IObserver observer:observers) {
            observer.update(msg);
        }
    }
}
