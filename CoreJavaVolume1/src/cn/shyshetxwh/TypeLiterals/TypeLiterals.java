package cn.shyshetxwh.TypeLiterals;

import java.util.ArrayList;

public class TypeLiterals {
    public static class Sample
    {
        ArrayList<Integer>nums;
        ArrayList<Character>chars;
        ArrayList<String>strings;
        public Sample()
        {
            nums=new ArrayList<>();
            nums.add(42);
            nums.add(1729);
            chars=new ArrayList<>();
            chars.add('H');
            chars.add('i');
            strings=new ArrayList<>();
            strings.add("Hello");
            strings.add("World");
        }
    }

    private static <T> String join(String separator,ArrayList<T>elements)
    {
        StringBuilder result = new StringBuilder();
        for (T element : elements) {
            if (result.length()>0)
            {
                result.append(separator);
            }
            result.append(element);
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception {
        Formatter formatter = new Formatter();

        formatter.forType(new TypeLiteral<ArrayList<Integer>>(){},(ArrayList<Integer> lst)-> join("",lst));
        formatter.forType(new TypeLiteral<ArrayList<Character>>(){},(ArrayList<Character> lst)->"\""+join("",lst)+"\"");
        Sample sample = new Sample();
        String s = formatter.formatFields(sample);
        System.out.println(s);
    }
}
