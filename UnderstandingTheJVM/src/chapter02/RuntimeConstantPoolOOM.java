package chapter02;

import java.util.HashSet;
import java.util.Set;

/**
 * FileName: RuntimeConstantPoolOOM
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/23 0023 16:32
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        int i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
