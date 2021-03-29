package v18.v18io;

import cn.shyshetxwh.util.Directory;

import java.io.*;

public class TestEOF {
    public static void main(String[] args) throws IOException {
        String path = Directory.findFilePath("TestEOF", "java");
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
        while ((dis.available())!=0){
            System.out.print((char)dis.readByte());
        }
    }
}
