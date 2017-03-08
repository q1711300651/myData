package TestAnnotation;

/**
 * Created by ww on 17/2/28.
 */
@TestAnnotationType(msg="class-annotation")
public class TestAnnotationDemo {
    public TestAnnotationDemo() {
    }
    public TestAnnotationDemo(String name){
        this.name=name;
    }

@TestAnnotationField(request = true)
    private String name;

    @TestAnnotationMethod(commonMethod="方法")
    public void method(){
        System.out.println("helloworld");
    }
}
