package TestRMI;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by ww on 17/3/4.
 */
public class Server {
    public static void main (String[]args) throws IOException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(9999);
        SubjectImpl subject = new SubjectImpl();
        registry.bind("subject",subject);
        System.in.read();
    }
}
