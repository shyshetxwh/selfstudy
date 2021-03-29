package cn.shyshetxwh.chapter06.item39;

import java.lang.reflect.Method;

/**
 * FileName: RunTests
 * Author:   admin+shyshetxwh
 * Date:     2021/03/28 22:52
 */
public class RunTests4 {
    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName("cn.shyshetxwh.chapter06.item39.Sample4");
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionTestContainer.class) || m.isAnnotationPresent(ExceptionRepeatTest.class)) {
                tests++;
                try {
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", m);
                } catch (Throwable t) {
                    Throwable exc = t.getCause();
                    int oldPassed = passed;
                    ExceptionRepeatTest[] excTests = m.getAnnotationsByType(ExceptionRepeatTest.class);
                    for (ExceptionRepeatTest excTest : excTests) {
                        if (excTest.value().isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }
                    if (passed == oldPassed)
                        System.out.printf("Test %s failed: %s %n", m, exc);
                }

            }
        }
        System.out.printf("Passed: %d,Failed:%d%n", passed, tests - passed);
    }
}
