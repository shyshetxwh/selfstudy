package cn.shyshetxwh.chapter06.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * FileName: RunTests
 * Author:   admin+shyshetxwh
 * Date:     2021/03/28 22:52
 */
public class RunTests {
    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName("cn.shyshetxwh.chapter06.item39.Sample");
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(MyTest.class)) {
                tests++;
                try {
                    m.invoke(null);
                    passed++;
                } catch (InvocationTargetException e) {
                    Throwable exc = e.getCause();
                    System.out.println(m + " failed: " + exc);
                } catch (Exception e) {
                    System.out.println("Invalid @MyTest: " + m);
                }
            }
        }
        System.out.printf("Passed: %d,Failed:%d%n", passed, tests - passed);
    }
}
