package designPatten.subcribe;

/**
 * Created by ww on 17/3/4.
 */
public interface ISubject {
     void attachObserver(IObserver observer);
    void detachObserver(IObserver observer);
    void notifyObservers(String msg);

}
