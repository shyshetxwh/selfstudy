package cn.shyshetxwh.stream;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CreatingStreams {

    public static <T> void show(String title, Stream<T> stream)
    {
        final int SIZE=10;
        List<T> first = stream.limit(SIZE + 1).collect(Collectors.toList());
        System.out.print(title+": ");
        for (int i = 0; i < first.size(); i++) {
            if (i>0)
            {
                System.out.print(",");
            }
            if (i<SIZE)
            {
                System.out.print(first.get(i));
            }
            else
            {
                System.out.print("...");
            }
        }
        System.out.println();
    }
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./CoreJavaVolume2/resource/alice30.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("\\PL+")).skip(1);
        show("words",words);

        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song",song);

        Stream<Object> silence = Stream.empty();
        show("silence",silence);

        Stream<String> echo = Stream.generate(() -> "Echo");
        show("echo",echo);

        Stream<Double> random = Stream.generate(Math::random);
        show("random",random);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.TEN, n -> n.add(BigInteger.ONE));
        show("integers",integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents).skip(1);
        show("wordsAnotherWay",wordsAnotherWay);


        try(Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            show("lines",lines);
        }

        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        Stream<Path> root = StreamSupport.stream(iterable.spliterator(), false);
        show("root",root);

        Iterator<Path> iterator = Paths.get("/usr/share/dict/words").iterator();
        Stream<Path> pathComponent = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
        show("pathComponent",pathComponent);
    }
}
