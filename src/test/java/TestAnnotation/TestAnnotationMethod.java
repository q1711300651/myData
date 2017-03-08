package TestAnnotation;

import java.lang.annotation.*;

/**
 * Created by ww on 17/2/28.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestAnnotationMethod {
    public String commonMethod();
}
