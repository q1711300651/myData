package TestUnit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Created by ww on 16/12/8.
 */
public class Test implements Comparable,Comparator {

    private int a;
   private String b;

    public Test() {
    }

    public Test(int a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    public int getA() {
        return a;
    }
    public void getA(int a){}

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public static void testStream(){
        Test.class.getClasses();
        ArrayList<String> words = new ArrayList<String>();
        words.add("a");
        words.add("b");
        words.stream().filter(s->s.startsWith("a"))
                        .forEach(System.out::println);
        Test t1 = new Test(1, "t1");
        Test t4=new Test(4,"T4");
        Test t2=new Test(2,"T2");
        Test t3=new Test(3,"T3");
        ArrayList<Test> tests = new ArrayList<>();
        tests.add(t1);
        tests.add(t4);
        tests.add(t2);
        tests.add(t3);

        tests.stream().filter(test->test.getA()>1).forEach(System.out::println);
        tests.stream().sorted();
        tests.stream().sorted((l1,l2)->(t1.getB()).compareTo(t2.getB()))
                        .forEach(System.out::println);
        long count = words.stream().map(String::toLowerCase).count();
        Stream<String> stringStream = tests.stream().map(t -> t.getB());
        boolean b1 = tests.stream().anyMatch(t -> t.getA() > 3);
        boolean b = tests.stream().noneMatch(test -> test.getA() < 1);

    }
    public static String aaaaa;
    public static void main(String[]args){
        System.out.println("test string >>>>>>>"+aaaaa);
        ArrayList<Test> tests = new ArrayList<>();
        Test test = new Test(1,"t");
        tests.add(test);
        test.setA(22);
        System.out.println(tests.get(0).getA());
        testStream();
        Test test2=test;
        Test t3=new Test();
        test2.setA(2);
        test2.setB("t2");
        System.out.println(test.getA()+test.getB()+test2.getB()+"||t3||"+t3.getA()+t3.getB());

    }
    public void test1(){
        Integer num=0,p=0;
        test2(p);
        num++;
        p+=900;
        System.out.println(p);

        ArrayList<String> strings = new ArrayList<String>();
        addStrings(strings);
        System.out.println(strings.toArray());

    }

    private void addStrings(ArrayList<String> strings) {
        for (int i = 0; i <10 ; i++) {
            strings.add(i+"");
            strings.add("");
        }
    }

    private void test2(Integer p) {

        for (int i = 0; i < 5; i++) {
            p++;
        }
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
