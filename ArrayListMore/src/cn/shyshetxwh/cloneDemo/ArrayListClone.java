package cn.shyshetxwh.cloneDemo;

import java.util.ArrayList;

public class ArrayListClone {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("一二三四五");
        list.add("上山打老虎");
        list.add("老虎不吃人");
        list.add("五四三二一");

        Object o = list.clone();
        System.out.println(list==o);
        System.out.println(list);
        System.out.println(o);
    }
}
