package v18.v18io;

import cn.shyshetxwh.util.Directory;

import java.io.*;
import java.net.URL;
import java.util.List;

public class BufferedInputFile {
    public static String read(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder builder = new StringBuilder();
        while((s=br.readLine())!=null){
            builder.append(s+"\n");
        }
        br.close();
        return builder.toString();
    }

    public static void main(String[] args) throws IOException {

        String path = Directory.findFilePath("BufferedInputFile","java");
        System.out.println(read(path));

    }
}
