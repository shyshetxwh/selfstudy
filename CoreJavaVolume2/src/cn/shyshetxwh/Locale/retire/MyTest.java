package cn.shyshetxwh.Locale.retire;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyTest {

    @Test
    public void test1() throws UnsupportedEncodingException {
        ResourceBundle bundle = ResourceBundle.getBundle("cn.shyshetxwh.Locale.retire.RetireStrings", Locale.US);
        String language = new String(bundle.getString("language").getBytes("ISO-8859-1"), "UTF-8");
        System.out.println("language = " + language);

    }
}
