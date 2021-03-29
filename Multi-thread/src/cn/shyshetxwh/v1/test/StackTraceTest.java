package cn.shyshetxwh.v1.test;

/**
 * FileName: StackTraceTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 20:02
 */
public class StackTraceTest {
    public void a() {
        b();
    }

    public void b() {
        c();
    }

    public void c() {
        d();
    }

    public void d() {
        e();
    }

    public void e() {
        StackTraceElement[] array = Thread.currentThread().getStackTrace();
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                StackTraceElement traceElement = array[i];
                System.out.println("className=" + traceElement.getClassName() +
                        " methodName=" + traceElement.getMethodName() +
                        " fileName=" + traceElement.getFileName() +
                        " lineNumber=" + traceElement.getLineNumber());
            }
        }
    }

    public static void main(String[] args) {
        StackTraceTest test = new StackTraceTest();
        test.a();
    }

}
