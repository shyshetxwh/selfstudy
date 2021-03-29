package cn.shyshetxwh.chapter06.item39;


/**
 * FileName: Sample
 * Author:   admin+shyshetxwh
 * Date:     2021/03/28 22:42
 */
public class Sample {
    @MyTest
    public static void m1() {
    }

    public static void m2() {
    }

    @MyTest
    public static void m3() {
        throw new RuntimeException("Boom");
    }

    public static void m4() {
    }

    @MyTest
    public void m5() {
    }

    public static void m6() {
    }

    @MyTest
    public static void m7() {
        throw new RuntimeException("Crach");
    }

    public static void m8() {
    }


}
