package TestRMI;

/**
 * Created by ww on 17/3/4.
 */
public class SubjectImpl  implements ISubject{

    @Override
    public String excute(String name) {
        return "Hello"+name;
    }
}
