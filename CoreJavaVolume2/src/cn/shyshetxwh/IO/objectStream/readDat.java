package cn.shyshetxwh.IO.objectStream;


import java.io.FileInputStream;

import java.io.IOException;

public class readDat {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream("employee3.dat");
        int r=0;
        while((r=fin.read())!=-1)
        {
            String format = String.format("%02X", r);
            System.out.println(format);
        }
        fin.close();
    }
}
