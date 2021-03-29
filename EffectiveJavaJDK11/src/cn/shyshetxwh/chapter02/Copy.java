package cn.shyshetxwh.chapter02;

import java.io.*;

/**
 * FileName: Copy
 * Author:   admin+shyshetxwh
 * Date:     2021/03/14 22:27
 */
public class Copy {
    private static final int BUFFER_SIZE = 8 * 1024;

    static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        copy(args[0], args[1]);
    }
}
