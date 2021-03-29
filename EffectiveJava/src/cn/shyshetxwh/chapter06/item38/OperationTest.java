package cn.shyshetxwh.chapter06.item38;

import java.util.Arrays;
import java.util.Collection;

/**
 * FileName: OperationTest
 * Author:   admin+shyshetxwh
 * Date:     2021/03/28 22:15
 */
public class OperationTest {
    public static void main(String[] args) {
        double x = 1.02;
        double y = 3.12;
        test(ExtendedOperation.class, x, y);
        System.out.println("-------------------------------");
        test2(Arrays.asList(ExtendedOperation.values()), x, y);
    }

    private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
        for (Operation op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }

    private static void test2(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}
