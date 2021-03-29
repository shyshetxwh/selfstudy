package cn.shyshetxwh.chapter02;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * FileName: UtilityClassTest
 * Author:   admin+shyshetxwh
 * Date:     2021/03/12 22:59
 */
public class UtilityClassTest {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("cn.shyshetxwh.chapter02.UtilityClass");
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            UtilityClass utilityClass = (UtilityClass) constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
