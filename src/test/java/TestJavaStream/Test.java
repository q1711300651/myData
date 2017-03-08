package TestJavaStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ww on 17/3/4.
 */
public class Test {
    private int age;
    private Integer length=0;
    private String name="defaultName";
    private List<Test> list=new ArrayList<>();
    private Map<String,Test> map=new HashMap<>();






    public Test() {
    }

    public Test(String name, Integer length, int age) {
        this.name = name;
        this.length = length;
        this.age = age;
    }

    public Test(int age, Integer length, String name, List<Test> list, Map<String, Test> map) {
        this.age = age;
        this.length = length;
        this.name = name;
        this.list = list;
        this.map = map;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Test> getList() {
        return list;
    }

    public void setList(List<Test> list) {
        this.list = list;
    }

    public Map<String, Test> getMap() {
        return map;
    }

    public void setMap(Map<String, Test> map) {
        this.map = map;
    }
}
