package cn.shyshetxwh.constructDemo;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ConstructDemo1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        ArrayList<String> list1 = new ArrayList<>(list);


    }
}
