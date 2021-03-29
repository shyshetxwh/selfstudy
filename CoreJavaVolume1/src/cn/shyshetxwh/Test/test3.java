package cn.shyshetxwh.Test;

import java.io.File;
import java.io.IOException;

public class test3 {
    public static void main(String[] args) throws IOException {
        File file=new File("./test/aaa.txt");
        if(!file.exists())
        {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        System.out.println(file.getPath().toString());
    }

}
