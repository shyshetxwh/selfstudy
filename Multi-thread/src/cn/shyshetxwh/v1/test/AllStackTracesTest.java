package cn.shyshetxwh.v1.test;

import java.util.Iterator;
import java.util.Map;

/**
 * FileName: AllStackTracesTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 20:15
 */
public class AllStackTracesTest {
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
        Map<Thread, StackTraceElement[]> map = Thread.currentThread().getAllStackTraces();
        if (map != null && map.size() != 0) {
            Iterator<Thread> threadIterator = map.keySet().iterator();
            while (threadIterator.hasNext()) {
                Thread eachThread = threadIterator.next();
                StackTraceElement[] array = map.get(eachThread);
                System.out.println("--------每个线程的基本信息--------");
                System.out.println("    线程名称：" + eachThread.getName());
                System.out.println("    线程堆栈数组长度：" + array.length);
                System.out.println("    线程状态：" + eachThread.getState());
                if (array.length != 0) {
                    System.out.println("    线程堆栈数组具体信息：");
                    for (int i = 0; i < array.length; i++) {
                        StackTraceElement traceElement = array[i];
                        System.out.println("        className=" + traceElement.getClassName() +
                                " methodName=" + traceElement.getMethodName() +
                                " fileName=" + traceElement.getFileName() +
                                " lineNumber=" + traceElement.getLineNumber());
                    }
                } else {
                    System.out.println("    没有信息");
                }
            }
        }
    }

    public static void main(String[] args) {
        AllStackTracesTest test = new AllStackTracesTest();
        test.a();
    }
}
