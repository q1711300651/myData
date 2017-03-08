package TestRMI;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by ww on 17/3/4.
 */
public class Client {
    public static void main (String[]args) throws IOException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",9999);
        ISubject subject = (ISubject) registry.lookup("subject");
        String hahaha = subject.excute("hahaha");
        System.out.println(hahaha);

    }
}
