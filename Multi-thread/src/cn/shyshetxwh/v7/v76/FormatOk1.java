package cn.shyshetxwh.v7.v76;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FileName: FormatOk1
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 21:24
 */

class DateTools {
    public static Date parse(String formatPattern, String dateString) throws ParseException {
        return new SimpleDateFormat(formatPattern).parse(dateString);
    }

    public static String format(String formatPattern, Date date) {
        return new SimpleDateFormat(formatPattern).format(date);
    }

}

class MyThread2 extends Thread {
    private SimpleDateFormat sdf;
    private String dateString;

    public MyThread2(SimpleDateFormat sdf, String dateString) {
        this.sdf = sdf;
        this.dateString = dateString;
    }

    @Override
    public void run() {
        try {
            Date date = DateTools.parse("yyyy-MM-dd", dateString);
            String newString = DateTools.format("yyyy-MM-dd", date);
            if (!newString.equals(dateString))
                System.out.println("ThreadName=" + Thread.currentThread().getName()
                        + " error from " + dateString + " to " + newString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

public class FormatOk1 {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArray = new String[]{
                "2000-01-01", "2000-01-02",
                "2000-01-03", "2000-01-04",
                "2000-01-05", "2000-01-06",
                "2000-01-07", "2000-01-08",
                "2000-01-09", "2000-01-10"
        };
        MyThread2[] threads = new MyThread2[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new MyThread2(sdf, dateStringArray[i]);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
