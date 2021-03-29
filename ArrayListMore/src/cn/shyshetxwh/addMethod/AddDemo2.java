package cn.shyshetxwh.addMethod;

import java.util.ArrayList;

public class AddDemo2 {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add(1,"赵六");
        System.out.println(list);

    }
}
