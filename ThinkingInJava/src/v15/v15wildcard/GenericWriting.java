package v15.v15wildcard;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class GenericWriting {
    static <T> void writeExact(List<T> list,T item)
    {
        list.add(item);
    }
    static <T> void writeWithWildcard(List<? super T>list,T item)
    {
        list.add(item);
    }

    static List<Apple> apples=new ArrayList<Apple>();
    static List<Fruit> fruits=new ArrayList<Fruit>();

    static void f1()
    {
        writeExact(apples,new Apple());
        writeExact(fruits,new Apple());
        System.out.println(apples);
        System.out.println(fruits);
    }

    static void f2()
    {
        writeWithWildcard(apples,new Apple());
        writeWithWildcard(fruits,new Apple());
        System.out.println(apples);
        System.out.println(fruits);
    }

    public static void main(String[] args) {
        f1();
        f2();
    }
}
