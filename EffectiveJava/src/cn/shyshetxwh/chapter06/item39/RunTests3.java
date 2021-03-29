package cn.shyshetxwh.chapter06.item39;

import java.lang.reflect.Method;

/**
 * FileName: RunTests
 * Author:   admin+shyshetxwh
 * Date:     2021/03/28 22:52
 */
public class RunTests3 {
    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName("cn.shyshetxwh.chapter06.item39.Sample3");
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionsTest.class)) {
                tests++;
                try {
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", m);
                } catch (Throwable t) {
                    Throwable exc = t.getCause();
                    int oldPassed = passed;
                    Class<? extends Exception>[] excTypes = m.getAnnotation(ExceptionsTest.class).value();
                    for (Class<? extends Exception> excType : excTypes) {
                        if (excType.isInstance(exc))
                            passed++;
                        break;
                    }
                    if (passed == oldPassed)
                        System.out.printf("Test %s failed: %s %n", m, exc);
                }

            }
        }
        System.out.printf("Passed: %d,Failed:%d%n", passed, tests - passed);
    }
}
