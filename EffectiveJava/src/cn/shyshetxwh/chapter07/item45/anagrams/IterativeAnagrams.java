package cn.shyshetxwh.chapter07.item45.anagrams;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * FileName: IterativeAnagrams
 * Author:   admin+shyshetxwh
 * Date:     2021/03/30 18:36
 */
public class IterativeAnagrams {
    public static void main(String[] args) throws IOException {
        File dictionary = new File(".\\EffectiveJava\\resources\\dictionary.txt");
        int minGroupSize = 4;

        Map<String, Set<String>> groups = new HashMap<>();
        try (Scanner s = new Scanner(dictionary)) {
            while (s.hasNext()) {
                String word = s.next();
                groups.computeIfAbsent(alphabetize(word), (unused) -> new TreeSet<>()).add(word);

            }
        }
        for (Set<String> group : groups.values()) {
            if (group.size() >= minGroupSize)
                System.out.println(group.size() + ": " + group);
        }
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
