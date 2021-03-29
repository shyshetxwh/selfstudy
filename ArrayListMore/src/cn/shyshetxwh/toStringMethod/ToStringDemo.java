package cn.shyshetxwh.toStringMethod;

import java.util.ArrayList;

public class ToStringDemo {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        String s = list.toString();
    }
}
