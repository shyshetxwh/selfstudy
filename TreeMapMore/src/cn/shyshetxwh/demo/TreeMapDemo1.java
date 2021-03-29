package cn.shyshetxwh.demo;

import java.util.TreeMap;

public class TreeMapDemo1 {
    public static void main(String[] args) {
        TreeMap<Integer,String> map=new TreeMap<>();

        map.put(123,"abc");
        map.put(456,"def");
        map.put(789,"qwe");
        map.put(123,"zxc");

        System.out.println(map);
    }
}
