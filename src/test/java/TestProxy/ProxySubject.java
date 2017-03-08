package TestProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by ww on 17/2/28.
 */
public class ProxySubject implements InvocationHandler {
    private CommonInterface subject;

    public ProxySubject() {
    }
    public ProxySubject(CommonInterface subject){
        this.subject=subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("111");
        method.invoke(subject,args);
        System.out.println("222");
        return null;
    }
}
