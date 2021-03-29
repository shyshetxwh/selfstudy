package cn.shyshetxwh.randomAccessDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo1 {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(i+"a");
        }

        //随机访问
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("随机访问用时： "+(end1-start1));

        //顺序访问
        long start2 = System.currentTimeMillis();
        Iterator<String> it = list.iterator();
        while (it.hasNext())
        {
            it.next();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("顺序访问用时： "+(end2-start2));

    }
}
