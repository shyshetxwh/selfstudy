package cn.shyshetxwh.chapter02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * FileName: TopLineWithDefault
 * Author:   admin+shyshetxwh
 * Date:     2021/03/14 22:38
 */
public class TopLineWithDefault {
    static String firstLineOfFile(String path, String defaultVal) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultVal;
        }
    }

    public static void main(String[] args) {
        System.out.println(firstLineOfFile(args[0], "read error"));
    }
}
