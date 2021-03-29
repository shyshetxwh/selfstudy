package cn.shyshetxwh.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {
    public static Stream<String> noVowels() throws IOException {
        Path path = Paths.get("./CoreJavaVolume2/resource/alice30.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        Stream<String> wordsStream = words.stream();
        return wordsStream.map(s->s.replaceAll("[aeiouAEIOU]",""));
    }

    public static <T> void show(String label, Set<T> set)
    {
        System.out.print(label+": "+set.getClass().getName());
        System.out.println("["+set.stream().limit(10).map(Object::toString).collect(Collectors.joining(", "))+"]");
    }

    public static void main(String[] args) throws IOException {
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while(iter.hasNext())
        {
            System.out.print(iter.next()+",");
        }
        System.out.println();

        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println("Object array"+numbers);

        try {
            Integer number = (Integer) numbers[0];
            System.out.println("number = " + number);
            System.out.println("下面这种类型转换会报错");
            Integer[] numbers1 = (Integer[]) numbers;
        } catch (ClassCastException e) {
            System.out.println(e);
        }

        Integer[] numbers2 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
        System.out.println("Integer array "+numbers2);

        Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
        show("noVowelSet",noVowelSet);

        TreeSet<String> noVowelTreeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet",noVowelTreeSet);

        String result = noVowels().limit(10).collect(Collectors.joining());
        System.out.println("Joining:"+result);

        result=noVowels().limit(10).collect(Collectors.joining(","));
        System.out.println("Joining with commas:"+result);

        IntSummaryStatistics summary = noVowels().collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        System.out.println("averageWordLength = " + averageWordLength);
        int maxWordLength = summary.getMax();
        System.out.println("maxWordLength = " + maxWordLength);
        System.out.println("ForEach:");
        noVowels().limit(10).forEach(System.out::println);


    }
}
