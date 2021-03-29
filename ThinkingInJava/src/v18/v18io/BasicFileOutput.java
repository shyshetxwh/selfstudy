package v18.v18io;

import cn.shyshetxwh.util.Directory;

import java.io.*;

public class BasicFileOutput {
    static String outFilename="BasicFileOutput.out";
    public static void main(String[] args) throws IOException {
        String path = Directory.findFilePath("BasicFileOutput");
        String outPath = new File(path).getParentFile().getAbsolutePath()+"/"+outFilename;

        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(path)));

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outPath)));

        int lineCount=1;
        String s;
        while ((s=in.readLine())!=null){
            out.println(lineCount++ + ": "+s);
        }
        out.close();

        System.out.println(BufferedInputFile.read(outPath));


    }
}
