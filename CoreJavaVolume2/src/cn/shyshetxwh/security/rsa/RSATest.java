package cn.shyshetxwh.security.rsa;

import cn.shyshetxwh.security.aes.CipherUtil;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.*;

public class RSATest {
    private static final int KEYSIZE=512;

    public static void main(String[] args) throws GeneralSecurityException, IOException, ClassNotFoundException {
        //1.产生一个密钥对
        genkey();
        //2.加密文件
        encrypt();
        //3.解密文件
        decrypt();
    }

    private static void decrypt()throws GeneralSecurityException, IOException, ClassNotFoundException {
        try(DataInputStream in=new DataInputStream(new FileInputStream("rsa_en"));
            ObjectInputStream keyIn=new ObjectInputStream(new FileInputStream("private.key"));
            FileOutputStream out=new FileOutputStream("rsa_de"))
        {
            int length = in.readInt();
            byte[] wrap = new byte[length];
            in.read(wrap,0,length);

            Key privateKey = (Key) keyIn.readObject();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.UNWRAP_MODE,privateKey);
            Key key = cipher.unwrap(wrap, "AES", Cipher.SECRET_KEY);

            cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,key);
            CipherUtil.crypt(in,out,cipher);
        }
    }

    private static void encrypt() throws GeneralSecurityException, IOException, ClassNotFoundException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecureRandom random = new SecureRandom();
        keygen.init(random);
        SecretKey key = keygen.generateKey();
        try(ObjectInputStream keyIn=new ObjectInputStream(new FileInputStream("public.key"));
            DataOutputStream out=new DataOutputStream(new FileOutputStream("rsa_en"));
            FileInputStream in=new FileInputStream("password.txt"))
        {
            Key publicKey = (Key) keyIn.readObject();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.WRAP_MODE,publicKey);
            byte[] wrap = cipher.wrap(key);
            out.writeInt(wrap.length);
            out.write(wrap);

            cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            CipherUtil.crypt(in,out,cipher);
        }
    }

    private static void genkey() throws GeneralSecurityException, IOException {
        KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = new SecureRandom();
        pairgen.initialize(KEYSIZE,random);
        KeyPair keyPair = pairgen.generateKeyPair();
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("public.key")))
        {
            out.writeObject(keyPair.getPublic());
        }
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("private.key")))
        {
            out.writeObject(keyPair.getPrivate());
        }
    }
}
