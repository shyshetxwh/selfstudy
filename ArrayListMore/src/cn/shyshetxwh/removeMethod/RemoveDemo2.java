package cn.shyshetxwh.removeMethod;

import java.util.ArrayList;

public class RemoveDemo2 {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        boolean b = list.remove("李四");

    }
}
