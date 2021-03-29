package cn.shyshetxwh.v7.v76;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FileName: FormatError
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 20:52
 */

class MyThread extends Thread {
    private SimpleDateFormat sdf;
    private String dateString;

    public MyThread(SimpleDateFormat sdf, String dateString) {
        this.sdf = sdf;
        this.dateString = dateString;
    }

    @Override
    public void run() {
        try {
            Date dateRef = sdf.parse(dateString);
            String newDateString = sdf.format(dateRef).toString();
            if (!newDateString.equals(dateString))
                System.out.println("ThreadName=" + Thread.currentThread().getName() +
                        " error from " + dateString + " to " + newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

public class FormatError {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArray = new String[]{
                "2000-01-01", "2000-01-02",
                "2000-01-03", "2000-01-04",
                "2000-01-05", "2000-01-06",
                "2000-01-07", "2000-01-08",
                "2000-01-09", "2000-01-10"
        };
        MyThread[] threads = new MyThread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new MyThread(sdf, dateStringArray[i]);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
