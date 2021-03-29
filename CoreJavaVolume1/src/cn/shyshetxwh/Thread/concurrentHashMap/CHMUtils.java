package cn.shyshetxwh.Thread.concurrentHashMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CHMUtils {
    public static ConcurrentHashMap<String,Long> map=new ConcurrentHashMap<>();

    public static void process(Path file)
    {
        try(Scanner in = new Scanner(file)) {
            while(in.hasNext())
            {
                String word = in.next();
                map.merge(word,1L,Long::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)) {
            return entries.collect(Collectors.toSet());
        }
    }
}
