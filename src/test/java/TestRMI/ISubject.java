package TestRMI;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * Created by ww on 17/3/4.
 */
public interface ISubject extends Remote,Serializable {
    public String excute(String name);
}
