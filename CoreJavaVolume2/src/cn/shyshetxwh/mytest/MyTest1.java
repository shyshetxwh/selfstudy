package cn.shyshetxwh.mytest;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class MyTest1 {
    public static void main(String[] args) throws IOException {
        String[] writerFormatNames = ImageIO.getWriterFormatNames();
        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("JPG");
        ImageWriter writer = iter.next();
        String[] names = writer.getOriginatingProvider().getFormatNames();
        for (String name : names) {
            System.out.println("name = " + name);

        }

    }
}
