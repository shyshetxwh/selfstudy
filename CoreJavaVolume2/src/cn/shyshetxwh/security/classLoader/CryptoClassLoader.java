package cn.shyshetxwh.security.classLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CryptoClassLoader extends ClassLoader {
    private int key;

    public CryptoClassLoader(int key) {
        this.key = key;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classBytes=null;
            classBytes=loadClassBytes(name);
            Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
            if (cl==null)throw new ClassNotFoundException(name);
            return cl;
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    private byte[] loadClassBytes(String name) throws IOException {
        String cname=name.replace(".","/")+".caesar";
        Path path = Paths.get(cname);
        byte[] bytes = Files.readAllBytes(path);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i]=(byte)(bytes[i]-key);
        }
        return bytes;
    }
}
