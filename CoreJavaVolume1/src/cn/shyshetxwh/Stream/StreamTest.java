package cn.shyshetxwh.Stream;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        ArrayList<String>list=new ArrayList<>();
        list.add("shirly");
        list.add("david");
        list.add("tom");
        Stream<String> stream = list.stream();
        //stream.map((String s)->{return new Person(s);}).forEach(System.out::println);
        Person[] people = stream.map(Person::new).toArray(Person[]::new);
        for (Person person : people) {
            System.out.println(person);
        }
    }
}
