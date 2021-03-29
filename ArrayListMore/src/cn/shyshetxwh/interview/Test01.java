package cn.shyshetxwh.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList频繁扩容导致添加性能急剧下降，如何处理？
 */
public class Test01 {
    public static void main(String[] args) {
        //创建集合对象
        List<String> list = new ArrayList<String>();
        //添加元素
        list.add("hello");
        list.add("PHP");
        list.add("Java");
        long startTime = System.currentTimeMillis();
        //需求:还需要添加10W条数据
        for (int i = 0; i < 1000000; i++) {
            list.add(i+"");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("未指定容量: "+ (endTime - startTime));

        //创建集合的时候指定足够大的容量
        List<String> list1 = new ArrayList<String>(100000);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list1.add(i+"");
        }
        endTime = System.currentTimeMillis();
        System.out.println("指定容量: "+ (endTime - startTime));
    }
}
