package cn.shyshetxwh.v4;

import java.util.concurrent.TimeUnit;

/**
 * FileName: SleepUtils
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/9 0009 22:53
 */
public class SleepUtils {
    public static final void millSecond(long millSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(millSeconds);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
    }

    public static final void second(long seconds) {
        millSecond(1000 * seconds);
    }
}
