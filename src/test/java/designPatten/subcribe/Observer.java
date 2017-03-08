package designPatten.subcribe;

/**
 * Created by ww on 17/3/4.
 */
public class Observer implements IObserver {
    private String name;
    private String msg;
    public Observer(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg) {
        System.out.println(name+msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
