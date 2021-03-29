package chapter02;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * FileName: DirectMemoryOOM
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/23 0023 17:02
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
