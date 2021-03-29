package cn.shyshetxwh.security.hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

public class Digest {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String filename="input.txt";
        String algname="SHA-1";
        for (Provider provider : Security.getProviders()) {
            for (Provider.Service s : provider.getServices()) {
                if (s.getType().equals("MessageDigest"))
                    System.out.println(s.getAlgorithm());
            }
        }

        MessageDigest alg = MessageDigest.getInstance(algname);
        byte[] input = Files.readAllBytes(Paths.get("CoreJavaVolume2/resource/input.txt"));
        byte[] hash = alg.digest(input);
        for (int i = 0; i < hash.length; i++) {

            System.out.printf("%02X ",hash[i]&0xFF);
        }
        System.out.println();
        for (int i = 0; i < hash.length; i++) {
            System.out.print(hash[i]+"  ");
        }
        System.out.println();
        for (int i = 0; i < hash.length; i++) {
            System.out.print(hash[i]&0xFF);
        }
        System.out.println();

    }
}
