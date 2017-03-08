package testIO;

import java.io.*;

/**
 * Created by ww on 17/2/15.
 */
public class ReadFile {
    public static void main(String[]args){
        ReadFile readFile = new ReadFile();
        String path="README.md";
        int[] charNum = readFile.getCharNum("README.md");
        System.out.println("小写字母数量"+charNum[0]+"大写字母数量"+charNum[1]);
        try {
            System.out.println("string1"+readFile.getString1(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("string2"+readFile.getString2(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private  String getString2(String path) throws FileNotFoundException {
        FileReader fileReader = new FileReader(path);
        int len=0;
        StringBuffer sb=new StringBuffer();
        try {
            while ((len=fileReader.read())>0){
                System.out.println(len);
               sb.append((char)len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  sb.substring(1);
    }

    private  String getString1(String path) throws FileNotFoundException {
//        FileReader fileReader = new FileReader(path);
        FileInputStream fileInputStream = new FileInputStream(path);
        int size= 0;
        try {
            size = fileInputStream.available();
            byte[] bytes = new byte[size];
            fileInputStream.read(bytes);
           return new String(bytes,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public int[] getCharNum(String path){
        int[]result=new int[2];

        try {
//            FileInputStream input = new FileInputStream(path);
            int next=0;
            String s="";
            byte[] b = new byte[1024];
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            while((s=bufferedReader.readLine())!=null){
                stringBuffer.append(s);
            };
            System.out.println(stringBuffer);
            String resultString=stringBuffer.toString();
            for (int i = 0; i <resultString.length() ; i++) {
                int charNum = resultString.codePointAt(i);
                if(charNum>=97&&charNum<=113){
                    result[0]++;
                }
                if(charNum>=65&&charNum<97){
                    result[1]++;
                }
            }
//            char[] chars = resultString.toCharArray();
//            chars[0].
//            for (:
//                 ) {
//
//            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
