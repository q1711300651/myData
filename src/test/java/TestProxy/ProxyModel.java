package TestProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ww on 17/2/28.
 */
public class ProxyModel {
    public static void main(String[]args){
        RealSubject realSubject = new RealSubject();
        Class<? extends RealSubject> aClass = realSubject.getClass();
        InvocationHandler handler = new ProxySubject(realSubject);
//        CommonInterface c= (CommonInterface) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), handler);
                CommonInterface c= (CommonInterface) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("111");
                        method.invoke(realSubject,args);
                        System.out.println("222");
                        return null;
                    }
                });
        c.commonMethod("world");
    }
}
