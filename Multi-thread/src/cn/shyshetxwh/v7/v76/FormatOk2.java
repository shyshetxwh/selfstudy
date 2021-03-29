package cn.shyshetxwh.v7.v76;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FileName: FormatOk2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 22:40
 */

class DateTools2 {
    private static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>();

    public static SimpleDateFormat getSimpleDateFormat(String datePattern) {
        SimpleDateFormat sdf = null;
        sdf = t1.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat(datePattern);
            t1.set(sdf);
        }
        return sdf;
    }
}

class MyThread3 extends Thread {
    private SimpleDateFormat sdf;
    private String dateString;

    public MyThread3(SimpleDateFormat sdf, String dateString) {
        this.sdf = sdf;
        this.dateString = dateString;
    }

    @Override
    public void run() {
        try {
            Date date = DateTools2.getSimpleDateFormat("yyyy-MM-dd").parse(dateString);
            String newString = DateTools2.getSimpleDateFormat("yyyy-MM-dd").format(date);
            if (!newString.equals(dateString))
                System.out.println("ThreadName=" + Thread.currentThread().getName()
                        + " error from " + dateString + " to " + newString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

public class FormatOk2 {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArray = new String[]{
                "2000-01-01", "2000-01-02",
                "2000-01-03", "2000-01-04",
                "2000-01-05", "2000-01-06",
                "2000-01-07", "2000-01-08",
                "2000-01-09", "2000-01-10"
        };
        MyThread3[] threads = new MyThread3[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new MyThread3(sdf, dateStringArray[i]);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
