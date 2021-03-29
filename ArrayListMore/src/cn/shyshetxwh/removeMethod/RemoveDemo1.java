package cn.shyshetxwh.removeMethod;

import java.util.ArrayList;

public class RemoveDemo1 {
    public static void main(String[] args) {
        ArrayList<String>list=new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        String value = list.remove(1);
        System.out.println(value);
        System.out.println(list);

    }
}
