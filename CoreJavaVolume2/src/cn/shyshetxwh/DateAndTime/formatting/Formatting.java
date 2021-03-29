package cn.shyshetxwh.DateAndTime.formatting;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

public class Formatting {
    public static void main(String[] args) {
        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));
        String formatted = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(apollo11launch);
        System.out.println("formatted = " + formatted);//1969-07-16T09:32:00-04:00

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        formatted = formatter.format(apollo11launch);
        System.out.println("formatted = " + formatted);//formatted = 1969年7月16日 上午09时32分00秒

        formatted = formatter.withLocale(Locale.FRENCH).format(apollo11launch);
        System.out.println("formatted = " + formatted);//formatted = 16 juillet 1969 09:32:00 EDT

        formatter=DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
        formatted=formatter.format(apollo11launch);
        System.out.println("formatted = " + formatted);//formatted = 星期三 1969-07-16 09:32

        LocalDate churchsBirthday = LocalDate.parse("1903-06-14");
        System.out.println("churchsBirthday = " + churchsBirthday);//churchsBirthday = 1903-06-14

        apollo11launch=ZonedDateTime.parse("1969-07-16 03:32:00-0400",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
        System.out.println("apollo11launch = " + apollo11launch);//apollo11launch = 1969-07-16T03:32-04:00

        for (DayOfWeek w : DayOfWeek.values()) {
            System.out.println(w.getDisplayName(TextStyle.SHORT,Locale.ENGLISH)+"");
        }
    }
}
