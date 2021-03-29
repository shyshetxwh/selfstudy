package cn.shyshetxwh.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class BinaryFile {
    public static byte[] read(File file) throws IOException{
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        try{
            byte[] data = new byte[in.available()];
            in.read(data);
            return data;
        }finally {
            in.close();
        }
    }

    public static byte[] read(String filename) throws IOException{
        return read(new File(filename).getAbsoluteFile());
    }

    public static void main(String[] args) throws IOException {
        String path = Directory.findFilePath("BinaryFile");
        byte[] bytes = read(path);
        for (byte b : bytes) {
            System.out.print((char)b);
        }
    }
}
