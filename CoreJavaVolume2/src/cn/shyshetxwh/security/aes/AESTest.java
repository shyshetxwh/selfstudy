package cn.shyshetxwh.security.aes;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {
        //第一步，生成秘钥
        generateKey();
        //第二步，加密文件
        encrypt();
        //第三步，解密文件
        decrypt();


    }

    private static void decrypt() throws IOException, ClassNotFoundException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, ShortBufferException, IllegalBlockSizeException {
        int mode= Cipher.DECRYPT_MODE;
        try(ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream("secret.key"));
            FileInputStream in=new FileInputStream("encryptedFile");
            FileOutputStream out=new FileOutputStream("decryptedFile"))
        {
            Key key = (Key) keyIn.readObject();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode,key);
            CipherUtil.crypt(in,out,cipher);
        }
    }

    private static void encrypt() throws IOException, ClassNotFoundException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, ShortBufferException, IllegalBlockSizeException {
        int mode= Cipher.ENCRYPT_MODE;
        try(ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream("secret.key"));
            FileInputStream in=new FileInputStream("password.txt");
            FileOutputStream out=new FileOutputStream("encryptedFile"))
        {
            Key key = (Key) keyIn.readObject();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode,key);
            CipherUtil.crypt(in,out,cipher);
        }
    }



    private static void generateKey() throws IOException, NoSuchAlgorithmException {
        //返回指定加密算法的密钥生成器对象
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //产生随机数，用于密钥的初始化
        SecureRandom random = new SecureRandom();
        //初始化密钥生成器
        keygen.init(random);
        //生成一个新的密钥
        SecretKey key = keygen.generateKey();
        //将密钥写入密钥文件中
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("secret.key")))
        {
            out.writeObject(key);
        }
    }
}
