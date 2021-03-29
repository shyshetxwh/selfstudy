package cn.shyshetxwh.chapter06.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * FileName: RunTests
 * Author:   admin+shyshetxwh
 * Date:     2021/03/28 22:52
 */
public class RunTests2 {
    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName("cn.shyshetxwh.chapter06.item39.Sample2");
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", m);
                } catch (InvocationTargetException e) {
                    Throwable exc = e.getCause();
                    Class<? extends Throwable> excType = m.getAnnotation(ExceptionTest.class).value();
                    if (excType.isInstance(exc))
                        passed++;
                    else
                        System.out.printf("Test %s failed: excepted %s, got %s%n", m, excType.getName(), exc);
                } catch (Exception e) {
                    System.out.println("Invalid @Test: " + m);
                }
            }
        }
        System.out.printf("Passed: %d,Failed:%d%n", passed, tests - passed);
    }
}
