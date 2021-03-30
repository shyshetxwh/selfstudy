package cn.shyshetxwh.chapter07.item45.anagrams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/**
 * FileName: HybridAnagrams
 * Author:   admin+shyshetxwh
 * Date:     2021/03/30 19:05
 */
public class HybridAnagrams {
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get(".\\EffectiveJava\\resources\\dictionary.txt");
        int minGroupSize = 4;

        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(groupingBy(word -> alphabetize(word))).values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(g -> System.out.println(g.size() + ": " + g));
        }
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
