package TestUnit;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Filter;

/**
 * Created by ww on 16/12/26.
 */
public interface ITest extends Consumer,Predicate,Filter {
    public void printName();
}
