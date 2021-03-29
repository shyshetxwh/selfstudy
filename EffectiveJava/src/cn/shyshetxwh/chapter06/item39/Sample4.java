package cn.shyshetxwh.chapter06.item39;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: Sample3
 * Author:   admin+shyshetxwh
 * Date:     2021/03/29 18:54
 */
public class Sample4 {
    @ExceptionRepeatTest(IndexOutOfBoundsException.class)
    @ExceptionRepeatTest(NullPointerException.class)
    public static void doubleBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }

    @ExceptionRepeatTest(IndexOutOfBoundsException.class)
    @ExceptionRepeatTest(NullPointerException.class)
    public static void good() {

    }
}
