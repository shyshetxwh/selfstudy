package cn.shyshetxwh.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: ListTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/16 0016 8:33
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }

        ArrayList<String> list1 = new ArrayList<>(list);
        System.out.println(list);
        System.out.println(list1);

        for (int i = 0; i < list.size(); i++) {
            list.set(i, "" + (i + 1));
        }

        System.out.println(list);
        System.out.println(list1);
    }
}
