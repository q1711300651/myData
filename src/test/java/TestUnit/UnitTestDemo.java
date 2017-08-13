package TestUnit;

import com.ww.entity.Customer;
import com.ww.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ww on 16/12/31.
 */
public class UnitTestDemo {

    @Before
    public void before() {
    }


    @Test
    public void testCollections() {
        List list = new ArrayList<>();
        System.out.println(list.isEmpty());
        List list2 = null;
        System.out.println(list2.isEmpty());
        String a;
//        System.out.println(a);
    }

    /**
     * 测试 serverSocket
     */
    @Test
    public void test1() {
        try {
            int num = 0;
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket socket = serverSocket.accept();
            System.out.println("test" + (++num));
            System.out.println(serverSocket.getLocalPort());
            socket.getInetAddress();
            socket.getChannel();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            StringBuffer stringBuffer = new StringBuffer();
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                stringBuffer.append((char) len);
            }
            System.out.println(stringBuffer.toString());

//            while (true) {
//                new Thread(new Runnable() {
//                    public void run() {
//                        System.out.println("a");
//                        OutputStream stream=new OutputStream() {
//                            @Override
//                            public void write(int b) throws IOException {
//                                write("abcd".getBytes());
//                            }
//                        };
//
//                    }
//                }).start();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试list排序
     */
    @Test
    public void testListSort() {
        Class<User> userClass = User.class;
        User user1 = new User();
        Class<? extends User> aClass = user1.getClass();

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setUserId(i + 1);
            user.setPassword(i + "");
            users.add(user);
        }
        System.out.println(users);
        users = null;
        List<User> collect = users.stream().filter(o1 -> o1.getUserId() > 2).collect(Collectors.toList());
//        users.stream().sorted(comparing(User::getUserId).reversed());
//        Collections.sort(users, new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                if(o1.getUserId()<o2.getUserId()){return 1;}
//                return -1 ;
//            }
//        });
        System.out.println(users);
        System.out.println(collect);
        System.out.println("");
    }

    /**
     * 测试map和continue
     */
    @Test
    public void test2() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 5000; i++) {
            String a = i + "";
            String b = i + 100 + "";
            map.put(a, b);
            if (i == 1) {
                continue;
            }
            a += "aaa";
        }
        System.out.println(map);
        System.out.println(map.size());
    }

    /*
    测试 反射reflect field
     */
    @Test
    public void test3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] declaredFields = Customer.class.getDeclaredFields();
        Field[] fields = Customer.class.getFields();
        Method[] declaredMethods = Customer.class.getDeclaredMethods();
        Method[] methods = Customer.class.getMethods();
        Customer customer = new Customer();
        customer.setId("100000");
        declaredFields[0].setAccessible(true);
        String o = (String) declaredFields[0].get(customer);
        Method sing = Customer.class.getMethod("sing", null);
        sing.invoke(new Customer(), new Object[0]);
        Method sing1 = Customer.class.getMethod("sing", String.class);
        sing1.invoke(new Customer(), "WANGFEI");

        Method sing2 = Customer.class.getMethod("sing");
    }


    /**
     * 测试数组相乘
     */
    @Test
    public void test10() {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{5, 6}, {7, 8}, {9, 10}};
        int[][] c = new int[10][10];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                for (int k = 0; k < a[i].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        double naN = Double.NaN;
        System.out.println(c);
    }

    @Test
    public void test13() {
        char a = '是';
    }

    @Test
    public void test3Des() {

    }
}
