package cn.shyshetxwh.interview;

import java.util.ArrayList;
import java.util.LinkedList;

public class Test03 {
    public static void main(String[] args) {
        //创建ArrayList集合对象
        ArrayList<String> arrayList = new ArrayList<String>();
        //添加500W个元素
        for (int i = 0; i < 1000000; i++) {
            arrayList.add(i+"XXX");
        }
        //获取开始时间
        long startTime = System.currentTimeMillis();
        //根据元素删除ArrayList集合元素
        //删除元素为 "5000XXX"
        boolean b = arrayList.remove("5000XXX");
        System.out.println("删除的状态: "+b);
        //获取结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("ArrayList集合删除元素的时间: "+(endTime-startTime));
        //创建LinkedList集合对象
        LinkedList<String> linkedList = new LinkedList<String>();
        //添加500W个元素
        for (int i = 0; i < 1000000; i++) {
            linkedList.add(i+"XXX");
        }
        //获取开始时间
        startTime = System.currentTimeMillis();
        //根据元素删除LinkedList集合元素
        //删除元素为 "5000XXX"
        b = linkedList.remove("5000XXX");
        System.out.println("删除的状态: "+b);
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList集合删除元素的时间: "+(endTime-startTime));
    }
}
