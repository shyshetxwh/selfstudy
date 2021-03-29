package cn.shyshetxwh.iteratorMethod;

import java.util.ArrayList;
import java.util.Iterator;

public class RemoveDemo1 {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        Iterator<String> it = list.iterator();

        while(it.hasNext())
        {
            String s = it.next();
            if (s.equals("李四"))
                it.remove();
        }

        System.out.println(list);
    }
}
