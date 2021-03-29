package cn.shyshetxwh.IO.zip;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipTest {
    public static void main(String[] args) throws IOException {
        String filename = ZipTest.class.getClassLoader().getResource("test.zip").getPath();
        String filename2 = ZipTest.class.getClassLoader().getResource("test2.zip").getPath();
        showContents(filename);
        System.out.println("===========");

        showContents2(filename2);


    }

    private static void showContents2(String filename) throws IOException {

        FileSystem fs = FileSystems.newFileSystem(Paths.get(filename.substring(1)), null);
        Files.walkFileTree(fs.getPath("/"),new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                for (String line : Files.readAllLines(file, Charset.forName("gbk"))) {
                    System.out.println("    "+line);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void showContents(String filename) throws IOException {
        try(ZipInputStream zin=new ZipInputStream(new FileInputStream(filename),Charset.forName("GBK"))){
            ZipEntry entry;
            while((entry=zin.getNextEntry())!=null)
            {
                System.out.println(entry.getName());
                Scanner sc = new Scanner(zin, "gbk");
                while(sc.hasNextLine())
                {
                    System.out.println("    "+sc.nextLine());
                }
                zin.closeEntry();
            }

        }

    }
}
