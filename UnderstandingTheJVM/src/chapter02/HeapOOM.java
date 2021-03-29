package chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: HeapOOM
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/23 0023 16:02
 */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
