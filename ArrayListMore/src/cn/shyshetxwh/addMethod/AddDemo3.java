package cn.shyshetxwh.addMethod;

import java.util.ArrayList;

public class AddDemo3 {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        ArrayList<String> list1=new ArrayList<>();
        list1.addAll(list);
    }
}
