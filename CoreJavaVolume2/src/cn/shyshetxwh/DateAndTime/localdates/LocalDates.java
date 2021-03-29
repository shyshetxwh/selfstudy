package cn.shyshetxwh.DateAndTime.localdates;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class LocalDates {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("today = " + today);//today = 2020-11-13
        LocalDate birthday = LocalDate.of(1988, 9, 5);
        birthday=LocalDate.of(1988, Month.SEPTEMBER,5);
        System.out.println("birthday = " + birthday);//birthday = 1988-09-05
        LocalDate programmersDay = LocalDate.of(2020, 1, 1).plusDays(255);
        System.out.println("programmersDay = " + programmersDay);//programmersDay = 2020-09-12

        LocalDate independenceDay = LocalDate.of(2020, Month.JULY, 4);
        LocalDate christmas = LocalDate.of(2020, Month.DECEMBER, 25);
        System.out.println("Until christmas: "+independenceDay.until(christmas));//P5M21D
        System.out.println("Until christmas: "+independenceDay.until(christmas, ChronoUnit.DAYS));//Until christmas: 174

        System.out.println(LocalDate.of(2016,1,31).plusMonths(1));//2016-02-29
        System.out.println(LocalDate.of(2016,3,31).minusMonths(1));//2016-02-29

        DayOfWeek dayOfWeek = LocalDate.of(1900, 1, 1).getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);//dayOfWeek = MONDAY
        System.out.println(dayOfWeek.getValue());//1
        System.out.println(DayOfWeek.SATURDAY.plus(3));//TUESDAY
    }
}
