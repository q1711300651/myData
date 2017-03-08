package designPatten.subcribe;

import org.junit.Test;

import java.util.List;

/**
 * Created by ww on 17/3/4.
 */
public class Main {
    @Test
    public void test(){
        Observer a = new Observer("A");
        Observer b = new Observer("B");
        Subject subject = new Subject();
        List<IObserver> observers = subject.getObservers();
        observers.add(a);
        observers.add(b);
        subject.notifyObservers("hello");
    }
}
