package TestAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by ww on 17/2/28.
 */
public class TestAnnotationMain {

    public static void main(String []args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InstantiationException {

        //type 类声明
        TestAnnotationDemo world = new TestAnnotationDemo("world");
        TestAnnotationType annotation = world.getClass().getAnnotation(TestAnnotationType.class);
        String msg = annotation.msg();
        System.out.println(msg);

        //field
        Field name = world.getClass().getDeclaredField("name");
        TestAnnotationField fieldAnno = name.getAnnotation(TestAnnotationField.class);
        boolean request = fieldAnno.request();
        System.out.println(request);

        //method
//        Method[] declaredMethods = world.getClass().getDeclaredMethods();
        Method declaredMethod=world.getClass().getMethod("method",new Class[0]);
        TestAnnotationMethod methodAnno = declaredMethod.getAnnotation(TestAnnotationMethod.class);
        String s = methodAnno.commonMethod();
        System.out.println(s);
        TestAnnotationDemo testAnnotationDemo = TestAnnotationDemo.class.newInstance();
        TestAnnotationMethod annotation1 = testAnnotationDemo.getClass().getMethod("method",new Class[0]).getAnnotation(TestAnnotationMethod.class);
        String s1 = annotation1.commonMethod();
        System.out.println(s1);
    }
}
