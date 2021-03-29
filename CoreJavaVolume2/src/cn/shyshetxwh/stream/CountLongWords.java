package cn.shyshetxwh.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import java.util.List;

public class CountLongWords {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./CoreJavaVolume2/resource/alice30.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        long count=0;

        Instant start1 = Instant.now();
        for (String word : words) {
            if (word.length()>12)
            {
               count++;
            }
        }
        System.out.println("count = " + count);
        Instant end1 = Instant.now();
        long time1 = Duration.between(start1, end1).toMillis();
        System.out.println("time1 = " + time1);

        Instant start2 = Instant.now();
        count = words.stream().filter(s -> s.length() > 12).count();
        System.out.println("count = " + count);
        Instant end2 = Instant.now();
        long time2 = Duration.between(start2, end2).toMillis();
        System.out.println("time2 = " + time2);

        Instant start3 = Instant.now();
        count = words.parallelStream().filter(s -> s.length() > 12).count();
        System.out.println("count = " + count);
        Instant end3 = Instant.now();
        long time3 = Duration.between(start3, end3).toMillis();
        System.out.println("time3 = " + time3);


    }
}
