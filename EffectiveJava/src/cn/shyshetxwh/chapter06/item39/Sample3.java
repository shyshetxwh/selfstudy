package cn.shyshetxwh.chapter06.item39;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: Sample3
 * Author:   admin+shyshetxwh
 * Date:     2021/03/29 18:54
 */
public class Sample3 {
    @ExceptionsTest({IndexOutOfBoundsException.class, NullPointerException.class})
    public static void doubleBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }

    @ExceptionsTest({IndexOutOfBoundsException.class, NullPointerException.class})
    public static void good() {
        
    }
}
