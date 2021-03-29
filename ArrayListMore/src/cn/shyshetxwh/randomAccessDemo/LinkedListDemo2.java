package cn.shyshetxwh.randomAccessDemo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo2 {
    public static void main(String[] args) {
        List<String> list=new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i+"a");
        }

        //测试随机访问
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("随机访问用时： "+(end1-start1));
        //测试顺序访问
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
