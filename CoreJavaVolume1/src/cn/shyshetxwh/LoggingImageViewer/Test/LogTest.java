package cn.shyshetxwh.LoggingImageViewer.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogTest {
    public static void main(String[] args) {
        Logger myLog = Logger.getLogger("MyLog");
        Logger mylog2=Logger.getLogger("MyLog");
        myLog.setLevel(Level.ALL);
        System.out.println(myLog.getLevel().getName());
        int i = myLog.hashCode();
        int j = mylog2.hashCode();
        System.out.println("i-j="+(i-j));


        String property = System.getProperty("java.util.logging.config.class");
        System.out.println(property);

    }
}
