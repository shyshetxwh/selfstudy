package cn.shyshetxwh.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class OptionalTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./CoreJavaVolume2/resource/alice30.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        Optional<String> optionalValue = words.stream().filter(s -> s.contains("fred")).findFirst();
        System.out.println(optionalValue.orElse("No Word")+" contains fred");

        Optional<Object> empty = Optional.empty();
        Object result = empty.orElse("N/A");
        System.out.println("result = " + result);

        result=empty.orElseGet(()-> Locale.getDefault().getDisplayName());
        System.out.println("result = " + result);

        try {
            result=empty.orElseThrow(IllegalStateException::new);
            System.out.println("result = " + result);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        optionalValue=words.stream().filter(s->s.contains("red")).findFirst();
        optionalValue.ifPresent(System.out::println);

        HashSet<String> results=new HashSet<>();
        optionalValue.ifPresent(results::add);
        System.out.println(results);

        Optional<Boolean> added = optionalValue.map(results::add);
        System.out.println(added);
        System.out.println(results);

        System.out.println(inverse(4.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(-1.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(0).flatMap(OptionalTest::squareRoot));

        Optional<Double> result2 = Optional.of(-4.0).flatMap(OptionalTest::inverse).flatMap(OptionalTest::squareRoot);
        System.out.println("result2 = " + result2);


    }

    public static Optional<Double> inverse(double x)
    {
        return x==0?Optional.empty():Optional.of(1/x);
    }

    public static Optional<Double> squareRoot(double x)
    {
        return x<0?Optional.empty():Optional.of(Math.sqrt(x));
    }
}
