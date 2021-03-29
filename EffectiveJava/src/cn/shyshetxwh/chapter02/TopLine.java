package cn.shyshetxwh.chapter02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * FileName: TopLine
 * Author:   admin+shyshetxwh
 * Date:     2021/03/14 22:34
 */
public class TopLine {
    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(firstLineOfFile(args[0]));
    }
}
