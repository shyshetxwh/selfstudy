package cn.shyshetxwh.examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * FileName: ListHelper
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/13 0013 20:06
 */
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent)
                list.add(x);
            return absent;
        }
    }
}
