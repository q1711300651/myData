package designPatten.singleton;

/**
 * Created by ww on 17/2/12.
 * 单例模式
 */
public class Single {
    private Single(){};
    public static Single single=new Single();
    public static Single getInstance(){
        if(single==null){
            single=new Single();
        }
        return single;
    }
}
