package TestProxy;

/**
 * Created by ww on 17/2/28.
 */
public class RealSubject implements CommonInterface{
    @Override
    public String commonMethod(String name) {
        System.out.println("hello"+name);
        return "Hello "+name;
    }
}
