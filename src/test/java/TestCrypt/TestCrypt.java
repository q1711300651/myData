package TestCrypt;

import org.junit.Test;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by ww on 17/2/23.
 */
public class TestCrypt {


    @Test
    public void des() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, InvalidKeySpecException {
        //定义密钥 AES 加密 密钥 Iccibtwicy_xykey CBC  偏移量同密钥 补位 PKCS5
//        {"version":"0.2","name":"CIBTrust","key":"12345678",
// "url":"http://168.3.21.155:8201/wbgxintuo/resources/download.zip"}
        String src="";
        String key="Iccibtwicy_xykey";
        encyptDes(src,key);


    }
    @Test
    public void testSHA1(){
        //
    }
    @Test
    public void testAES(){

    }
    @Test
    public void test3Des() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
//      SecretKey key=initKey();
//        System.out.println(">>>des.key"+fromBytesToHex(key.getEncoded())+"<<<");
//        byte[] encrypt = encrypt("aaa", key);
//        decrypt(encrypt,key);
        SecretKey secretKey = init3DesKey();
        System.out.println(">>>3des.key"+fromBytesToHex(secretKey.getEncoded())+"<<<");
        byte[] aaas = this.encypt3Des("aaa", secretKey);
        BASE64Encoder encoder=new BASE64Encoder();
//        String encode1 = encoder.encode(aaas);
        String s = new String(aaas);
        System.out.println(s);
        this.decrypt3Des(aaas, secretKey);
    }
    @Test
    public void testMd5String() throws NoSuchAlgorithmException, IOException {
        String data="aa";
        this.md5String(data);
        md5File("README.md");
    }
    private void encyptDes(String src,String key) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        DESKeySpec desKeySpec = new DESKeySpec(src.getBytes("utf-8"));
        SecretKeyFactory des = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = des.generateSecret(desKeySpec);
        IvParameterSpec ivParameterSpec = new IvParameterSpec("IV".getBytes("utf-8"));
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,ivParameterSpec);
        byte[] bytes = cipher.doFinal(src.getBytes());

    }
    private SecretKey initKey() throws NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        KeyGenerator keyGenerate = KeyGenerator.getInstance("DES");
//        keyGenerate.init(56); 不加这个会默认
        return  keyGenerate.generateKey();
    }
    private byte[] encrypt(String data,SecretKey key) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher=Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] result = cipher.doFinal(data.getBytes());
        System.out.println(">>>"+fromBytesToHex(key.getEncoded())+">>>");
        System.out.println(">>>加密后的十六进制字符串"+fromBytesToHex(result)+">>>");
        return result;
    }
    private byte[] decrypt(byte[]encryptData, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher=Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE,key);
        byte[] result = cipher.doFinal(encryptData);
        System.out.println(">>>解密后的字符串"+new String(result)+"<<<");
        return result;

    }
    public  String fromBytesToHex(byte[] resultBytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < resultBytes.length; i++) {
            if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1) {
                builder.append("0").append(
                        Integer.toHexString(0xFF & resultBytes[i]));
            } else {
                builder.append(Integer.toHexString(0xFF & resultBytes[i]));
            }
        }
        return builder.toString();
    }
    
    private SecretKey generate3DesKey(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException {
        DESedeKeySpec keySpec = new DESedeKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory deSede = SecretKeyFactory.getInstance("DESede");
        return deSede.generateSecret(keySpec);
    }

    private SecretKey init3DesKey() throws NoSuchAlgorithmException {

//        DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
//        SecretKey desKey = keyFactory.generateSecret(dks);
        
        KeyGenerator desede = KeyGenerator.getInstance("DESede");
        //desede.init(168); 可以指定112或者168 不指定默认168
        return desede.generateKey();
    }

    private byte[] encypt3Des(String data, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher=Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] result = cipher.doFinal(data.getBytes());
        System.out.println(">>>加密后的16进制字符串"+this.fromBytesToHex(result)+"<<<");
        return result;
    }
    private byte[] decrypt3Des(byte[]dataBytes,SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher deSede = Cipher.getInstance("DESede");
        deSede.init(Cipher.DECRYPT_MODE,key);
        byte[] result = deSede.doFinal(dataBytes);
        System.out.println(">>>解密后的数据"+new String(result)+"<<<");
        return result;
    }

    private String md5String(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest=MessageDigest.getInstance("MD5");
        digest.update(data.getBytes());
        byte[] resultBytes = digest.digest();
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String result = base64Encoder.encode(resultBytes);
        System.out.println(">>>"+result+"<<<");
        return  result;
    }
    private String md5File(String path) throws NoSuchAlgorithmException, IOException {
        File file = new File(path);
        System.out.println("文件是否存在"+file.exists());
        MessageDigest md=MessageDigest.getInstance("md5");
        FileInputStream fileInputStream = new FileInputStream(path);
        byte[]b=new byte[10];
        int len=0;
        while((len=fileInputStream.read(b))>0){
            md.update(b,0,len);
        }
        byte[] resultBytes = md.digest();
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String result = base64Encoder.encode(resultBytes);
        System.out.println(">>>文件校验码"+result+"<<<");
        return result;
    }

}
