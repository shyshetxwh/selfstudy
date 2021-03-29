package v18.v18zip;

import java.io.*;
import java.util.zip.*;

public class ZipCompress {
    public static void main(String[] args) throws IOException {
        String path="./ThinkingInJava/src/v18/v18zip/test.zip";
        FileOutputStream f = new FileOutputStream(path);
        CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zos);
        zos.setComment("a test of Java Zipping.");
        BufferedReader in = new BufferedReader(new FileReader("./ThinkingInJava/src/v18/v18zip/ZipCompress.java"));
        zos.putNextEntry(new ZipEntry("./ThinkingInJava/src/v18/v18zip/ZipCompress.java"));
        int c;
        while((c=in.read())!=-1)
        {
            out.write(c);
        }
        in.close();
        out.close();
        System.out.println("CheckSum: "+csum.getChecksum().getValue());

        FileInputStream fi = new FileInputStream(path);
        CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
        ZipInputStream in2 = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(in2);
        ZipEntry ze;
        while((ze=in2.getNextEntry())!=null)
        {
            System.out.println(ze);
            int x;
            while((x=bis.read())!=-1)
            {
                System.out.print((char) x);
            }
        }
        System.out.println(csumi.getChecksum().getValue());
        bis.close();

    }
}
