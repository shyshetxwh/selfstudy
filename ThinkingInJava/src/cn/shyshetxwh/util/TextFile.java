package cn.shyshetxwh.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {
    public static String read(String filename){
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(filename).getAbsoluteFile()));
            try{
                String s;
                while ((s=in.readLine())!=null){
                    builder.append(s);
                    builder.append("\n");
                }
            }finally {
                in.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void write(String filename,String text){
        try{
            PrintWriter out = new PrintWriter(new File(filename).getAbsoluteFile());
            try{
                out.print(text);
            }finally {
                out.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public TextFile(String filename,String splitter) {
        super(Arrays.asList(read(filename).split(splitter)));
        if (get(0).equals("")) remove(0);
    }

    public TextFile(String filename) {
        this(filename,"\n");
    }

    public void write(String filename){
        try {
            PrintWriter out = new PrintWriter(new FileWriter(new File(filename).getAbsoluteFile()));
            try{
                for (String s : this) {
                    out.println(s);
                }
            }finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String path = Directory.findFilePath("TextFile");
        String outPath = new File(path).getParentFile().getAbsolutePath()+"/test.txt";
        String outPath2 = new File(path).getParentFile().getAbsolutePath()+"/test2.txt";
        String file = read(path);
        write(outPath,file);

        TextFile textFile = new TextFile(outPath);
        textFile.write(outPath2);

        TreeSet<String> words=new TreeSet<>(new TextFile(path,"\\W+"));
        System.out.println(words.headSet("a"));

    }
}
