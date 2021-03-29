package cn.shyshetxwh.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ParallelStreams {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./CoreJavaVolume2/resource/alice30.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));

        int[] shortWords = new int[10];
        wordList.parallelStream().forEach(s->{
            if (s.length()<10)
            {
                shortWords[s.length()]++;
            }
        });
        System.out.println(Arrays.toString(shortWords));
        System.out.println("--------------------------");

        Arrays.fill(shortWords,0);
//        System.out.println(Arrays.toString(shortWords));
//        System.out.println("--------------------------");
        wordList.parallelStream().forEach(s->{
            if (s.length()<10)
            {
                shortWords[s.length()]++;
            }
        });
        System.out.println(Arrays.toString(shortWords));
        System.out.println("--------------------------");

        Map<Integer, Long> shortWordCounts = wordList.parallelStream().filter(s -> s.length() < 10).collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(shortWordCounts);
        System.out.println("--------------------------");

        ConcurrentMap<Integer, List<String>> result = wordList.parallelStream().collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(14));
        System.out.println("--------------------------");

        result = wordList.parallelStream().collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(14));
        System.out.println("--------------------------");

        ConcurrentMap<Integer, Long> wordCounts = wordList.parallelStream().collect(Collectors.groupingByConcurrent(String::length, Collectors.counting()));
        System.out.println(wordCounts);

    }
}
