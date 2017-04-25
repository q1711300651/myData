package testEnum;

/**
 * Created by ww on 17/3/23.
 */
public enum TestEnumDemo {
    INSTANCE;
    private TestEnumDemo(){
        System.out.println("testEnum");
    }
    public void init(){
        System.out.println("init");
    }

}
