package TestJavaStream;

import org.junit.Assert;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by ww on 17/3/4.
 */
public class TestStream {
    Test hello;
    @Before
    public void before(){
        hello = new Test("hello", 100, 30);
        ArrayList<Test> tests = new ArrayList<>();
        ArrayList<Test> hellos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Test test = new Test("hello" + i, new Random().nextInt(100), 100);
            tests.add(test);
        }
        hello.setList(tests);

    }
    /**
     * 测试list排序
     */
    @org.junit.Test
    public void test1(){
        Assert.assertEquals("great",30,hello.getAge());
        Optional<Test> first = hello.getList().stream().findFirst();
        List<Test> collect = hello.getList().stream().filter(o -> o.getAge() != 100).collect(Collectors.toList());
        System.out.println(hello);

    }
}
