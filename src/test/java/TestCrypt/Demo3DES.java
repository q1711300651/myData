package TestCrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.IOException;

/**
 * 系统名称: 中信网络科技手机移动平台<br />
 * 模块名称: 基础平台-公共组件-安全包<br />
 * 软件版权: Copyright (c) 2014 中信网络科技<br />
 * 功能说明: 3DES加解密<br />
 * 系统版本: 1.0<br />
 * 相关文档: <br />
 * .<br />
 * <b>修订记录</b>
 *
 * @author su
 * @version 1.0
 * @since 1.0
 */
public class Demo3DES {
    private static final String ALGORITHM = "DESede/ECB/PKCS5Padding";





    public static void main(String[]args) throws IOException {
        byte[] encrypt1 = encrypt("853542176233".getBytes("UTF-8"), "hundsun1", "hundsun1", "hundsun1");
        byte[] encrypt2 = encrypt("HVMMZMKVHPVRSPVGWRTHWNMEGVEUXNZK".getBytes("UTF8"), "hundsun1", "hundsun1", "hundsun1");
        BASE64Encoder encoder=new BASE64Encoder();
        String encode1 = encoder.encode(encrypt1);
        String encode2 = encoder.encode(encrypt2);
        BASE64Decoder decoder=new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(encode1);
        byte[] decrypt = decrypt(bytes, "hundsun1", "hundsun1", "hundsun1");
        String s = new String(decrypt);
        System.out.println(s);

        System.out.println(encode1);
        System.out.println(encode2);
    }
    public static byte[] encrypt(byte[] src,
                                 String key1,
                                 String key2,
                                 String key3) {
        if (src == null || src.length <= 0
                || key1 == null || key1.length() < 1
                || key2 == null || key2.length() < 1
                || key3 == null || key3.length() < 1){
            // $ERROR:MPSE003==3DES加密输入参数为空==请联系客服
        }
        if (key1.length() < 8){
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 8 - key1.length(); i++)
                sb.append("0");
            key1 = key1 + sb.toString();
        }
        if (key2.length() < 8){
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 8 - key2.length(); i++)
                sb.append("0");
            key2 = key2 + sb.toString();
        }
        if (key3.length() < 8){
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 8 - key3.length(); i++)
                sb.append("0");
            key3 = key3 + sb.toString();
        }
        src = encrypt(src,key1+key2+key3);
        return src;
    }

    /**
     * 函数功能: 对数据3DES解密
     *
     * @param src
     *            待解密的数据
     * @param key1
     *            第一个密钥
     * @param key2
     *            第二个密钥
     * @param key3
     *            第三个密钥
     * @return the string 输出参数: 返 回 值:String
     */
    public static byte[] decrypt(byte[] src,
                                 String key1,
                                 String key2,
                                 String key3) {
        if (src == null || src.length < 1
                || key1 == null || key1.length() < 1
                || key2 == null || key2.length() < 1
                || key3 == null || key3.length() < 1){
            // $ERROR:MPSE004==3DES解密输入参数为空==请联系客服
        }
        if (key1.length() < 8){
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 8 - key1.length(); i++)
                sb.append("0");
            key1 = key1 + sb.toString();
        }
        if (key2.length() < 8){
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 8 - key2.length(); i++)
                sb.append("0");
            key2 = key2 + sb.toString();
        }
        if (key3.length() < 8){
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 8 - key3.length(); i++)
                sb.append("0");
            key3 = key3 + sb.toString();
        }
        src = decrypt(src,key1+key2+key3);
        return src;
    }

    /**
     * 函数功能: 对数据3DES加密
     * @param src 待加密内容
     * @param key 密钥
     * @return 密文
     */
    public static byte[] encrypt(byte[] src, String key) {
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey desKey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            return cipher.doFinal(src);
        } catch (Exception e) {
        }

        return null;
    }

    /**
     * 函数功能: 对数据3DES解
     * @param src 待解密内容
     * @param key 密钥
     * @return 明文
     */
    public static byte[] decrypt(byte[] src, String key) {
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey desKey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, desKey);
            return cipher.doFinal(src);
        } catch (Exception e) {
        }

        return null;
    }
}
