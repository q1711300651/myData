package TestCrypt;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by ww on 17/8/21.
 */
public class TestAES {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    @Test
   public  void testAES() throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeyException, DecoderException, InvalidAlgorithmParameterException {
        String pwd="Iccibtwicy_xykey";
        String iv="Iccibtwicy_xykey";
        byte[] en = encryptAES("12345",iv, pwd);
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(en);
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] de = decryptAES(base64Decoder.decodeBuffer(encode),iv, pwd);
        String result = new String(de);


    }
    byte[] encryptAES(String content,String iv,String password) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, DecoderException, InvalidAlgorithmParameterException {
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        KeyGenerator generator=KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom(password.getBytes("utf-8"));
        generator.init(secureRandom);
//        byte[] hexbytes = hexDecode(iv);
        cipher.init(Cipher.ENCRYPT_MODE,generator.generateKey(),new IvParameterSpec(iv.getBytes()));
        return cipher.doFinal(content.getBytes("UTF-8"));
    }

    byte[] hexDecode(String iv) throws UnsupportedEncodingException, DecoderException {
        return Hex.decodeHex(iv.toCharArray());
    }
    public  String hexEncode(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }

    byte[] decryptAES(byte[] content,String iv,String password) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        KeyGenerator generator=KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom(password.getBytes("utf-8"));
        generator.init(secureRandom);
        cipher.init(Cipher.DECRYPT_MODE,generator.generateKey());
        return cipher.doFinal(content);
    }
}
