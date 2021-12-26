import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Encryption {
    private static String sKey="aa@bb.com"; // encryption key
    // create a secret key
    private static SecretKey makeKeyFactory() throws Exception{
         SecretKeyFactory des = SecretKeyFactory.getInstance("DES");// DES algorithm
         SecretKey secretKey = des.generateSecret(new DESKeySpec(sKey.getBytes()));
         return secretKey;
    }
    //get an encrypted string using DES algorithm
    public static String encrypt(String text){
            Cipher cipher;
            try {
                cipher = Cipher.getInstance("DES");
                SecretKey secretKey = makeKeyFactory();
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                return new String(Base64.getEncoder().encode(cipher.doFinal(text.getBytes())));    
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }
    // retrive a normal string from an encrypted string
    public static String decrypt(String text){
            Cipher cipher;
            try {
                cipher = Cipher.getInstance("DES");
                SecretKey secretKey = makeKeyFactory();
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                return new String(cipher.doFinal(Base64.getDecoder().decode(text.getBytes())));
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }    
}