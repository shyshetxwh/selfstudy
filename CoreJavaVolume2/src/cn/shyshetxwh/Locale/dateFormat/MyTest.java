package cn.shyshetxwh.Locale.dateFormat;

import org.junit.Test;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class MyTest {
    @Test
    public void test1()
    {
        String label="SHORT";
        Class<?>cl= FormatStyle.class;
        try {
            Field f = cl.getField(label);
            System.out.println("f = " + f);//f = public static final java.time.format.FormatStyle java.time.format.FormatStyle.SHORT
            FormatStyle value = (FormatStyle) f.get(cl);
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
