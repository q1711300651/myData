package TestConcurrent;

/**
 * Created by ww on 17/8/27.
 */
public class TestViolate extends Thread {
    public static void main (String[]args){
        new TestViolate("111").start();
        new TestViolate("222").start();

    }

    public TestViolate(String name) {
        super(name);
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i <200 ; i++) {
            if(i==100){
            Thread.yield();
            }
            System.out.println(getName()+"第"+i+"个");

        }
    }
}
