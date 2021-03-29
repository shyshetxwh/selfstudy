package cn.shyshetxwh.chapter06.item39;

/**
 * FileName: Sample2
 * Author:   admin+shyshetxwh
 * Date:     2021/03/29 18:40
 */
public class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    public static void m1() {
        int i = 0;
        i = i / i;
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m2() {
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m3() {
        
    }
}
