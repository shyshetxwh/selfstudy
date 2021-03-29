package cn.shyshetxwh.v1.test;

/**
 * FileName: DumpTraceTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 20:12
 */
public class DumpTraceTest {
    public void a() {
        b();
    }

    private void b() {
        c();
    }

    private void c() {
        d();
    }

    private void d() {
        e();
    }

    private void e() {
        int age = 0;
        age = 100;
        if (age == 100)
            Thread.dumpStack();
    }

    public static void main(String[] args) {
        DumpTraceTest test = new DumpTraceTest();
        test.a();
    }

}
