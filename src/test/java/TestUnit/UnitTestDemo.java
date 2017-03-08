package TestUnit;

import com.ww.entity.Customer;
import com.ww.entity.User;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
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
    public void testCollections(){
        List list = new ArrayList<>();
        System.out.println(list.isEmpty());
        List list2=null;
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
            serverSocket.accept();
            System.out.println("test" + (++num));
            System.out.println(serverSocket.getLocalPort());
            while (true) {
                new Thread(new Runnable() {
                    public void run() {
                        System.out.println();
//                        OutputStream stream=;
//                        stream.write("aa");
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 测试list排序
     */
    @Test
    public void testListSort(){
        Class<User> userClass = User.class;
        User user1 = new User();
        Class<? extends User> aClass = user1.getClass();

        List<User>users=new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            User user = new User();
            user.setUserId(i+1);
            user.setPassword(i+"");
            users.add(user);
        }
        System.out.println(users);
        users=null;
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
     * 测试 jxl excel 导入(写)
     */
    @Test
    public void test4() throws IOException, WriteException {
        File file = new File("testjxl.xls");
        file.createNewFile();
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        WritableSheet test1 = workbook.createSheet("test1", 0);
        WritableSheet test2 = workbook.createSheet("test2", 1);
        Label label = new Label(0, 1, "test11111");
        test1.addCell(label);
        workbook.write();
        workbook.close();
    }

    /**
     * 测试jxl 导出(读)
     */
    @Test
    public void test5() throws IOException, BiffException {
        File file = new File("testjxl.xls");
        Workbook workbook = Workbook.getWorkbook(file);
        List<Cell> cells = new ArrayList<>();
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheet(i);
            for (int j = 0; j < sheet.getRows(); j++) {
                for (int k = 0; k < sheet.getColumns(); k++) {
                    Cell cell = sheet.getCell(k, j);
                    cells.add(cell);
                    System.out.println(cell.toString());
                }
            }
        }

    }

    /**
     * 测试 poi 导入
     */
    @Test
    public void test6() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        String[] titles = {"id", "name", "age"};
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }
        File file = new File("testpoiexcel.xls");
        file.createNewFile();
        OutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();
    }

    /**
     * 测试poi 导入 excel
     *
     * @throws IOException
     */
    @Test
    public void test8() throws IOException {
        File file = new File("testpoiexcel.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
        if (workbook.getNumberOfSheets() > 0) {
            HSSFSheet sheetAt = workbook.getSheetAt(0);
            int firstRowNum = sheetAt.getFirstRowNum();
            int lastRowNum = sheetAt.getLastRowNum();
            for (int i = firstRowNum; i <= lastRowNum; i++) {
                HSSFRow row = sheetAt.getRow(i);
                short lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    HSSFCell cell = row.getCell(j);
                    System.out.println(cell.getStringCellValue());
                }
            }

        }
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
    public void test13(){
        char a='是';
    }

    @Test
    public void test3Des(){

    }
}
