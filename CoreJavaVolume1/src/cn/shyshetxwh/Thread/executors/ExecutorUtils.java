package cn.shyshetxwh.Thread.executors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutorUtils {
    /**
     * 在一个路径代表的文件中搜寻指定单词出现的次数
     *
     * @param word 搜索的关键词
     * @param path 搜索的路径
     * @return 关键词出现的次数
     */
    public static long occurrences(String word, Path path) {
        try (Scanner in = new Scanner(path)) {
            long count = 0;
            while (in.hasNext()) {
                if (in.next().equals(word)) {
                    count++;
                }
            }
            return count;

        } catch (IOException e) {
            return 0;
        }
    }

    /**
     * 把指定根目录下的文件转换为set集合
     *
     * @param rootDir 根目录
     * @return 文件路径集合
     */
    public static Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)) {
            return entries.filter(Files::isRegularFile).collect(Collectors.toSet());
        }
    }


    /**
     * 一个文件里是否有这个关键字，如果有，则返回这个路径
     *
     * @param word
     * @param path
     * @return
     */
    public static Callable<Path> searchForTask(String word, Path path) {

        return () -> {
            try (Scanner in = new Scanner(path)) {
                while (in.hasNext()) {
                    if (in.next().equals(word)) {
                        return path;
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Search in " + path + " canceled.");
                        return null;
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }
}


