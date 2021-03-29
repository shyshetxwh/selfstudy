package v15.v15wildcard;

import java.util.Arrays;
import java.util.List;

public class GenericReading {
    static List<Apple> apples= Arrays.asList(new Apple());
    static List<Fruit> fruits=Arrays.asList(new Fruit());

    static <T> T readExact(List<T> list){
        return list.get(0);
    }

    static <T> T readWildcard(List<? extends T> list){
        return list.get(0);
    }


    static class Reader<T>{
        T readExact(List<T> list){
            return list.get(0);
        }
    }

    static class CovariantReader<T>{
        T readCovariant(List<? extends T> list){
            return list.get(0);
        }
    }

    static void f1(){
        Apple a = readExact(apples);
        Fruit f = readExact(fruits);
        f=readExact(apples);
    }

    static void f2(){
        Apple a = readWildcard(apples);
        Fruit f = readWildcard(fruits);
        f=readWildcard(apples);
    }

    static void f3(){
        Reader<Fruit> reader = new Reader<>();
        //reader.readExact(apples);
        Fruit f = reader.readExact(fruits);
    }

    static void f4(){
        CovariantReader<Fruit> reader = new CovariantReader<>();
        Apple a = (Apple) reader.readCovariant(apples);
        Fruit f = reader.readCovariant(fruits);
    }

    public static void main(String[] args) {
        f1();
        f2();
        f3();
        f4();
    }
}
