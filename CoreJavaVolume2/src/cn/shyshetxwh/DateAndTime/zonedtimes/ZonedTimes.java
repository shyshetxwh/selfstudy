package cn.shyshetxwh.DateAndTime.zonedtimes;


import java.time.*;


public class ZonedTimes {
    public static void main(String[] args) {
        ZonedDateTime birthday = ZonedDateTime.of(1988, 9, 5, 9, 32, 0, 0, ZoneId.of("Asia/Shanghai"));
        System.out.println("birthday = " + birthday);//birthday = 1988-09-05T09:32+09:00[Asia/Shanghai]
        Instant instant = birthday.toInstant();
        System.out.println("instant = " + instant);//instant = 1988-09-05T00:32:00Z
        ZonedDateTime utc = instant.atZone(ZoneId.of("UTC"));
        System.out.println("utc = " + utc);//utc = 1988-09-05T00:32Z[UTC]

        //夏令时，会跳过去一个小时，2013.3.31 0200  -2013.10.27 0300
        ZonedDateTime skipped = ZonedDateTime.of(LocalDate.of(2013, 3, 31), LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        System.out.println("skipped = " + skipped);//skipped = 2013-03-31T03:30+02:00[Europe/Berlin]
        ZonedDateTime ambiguous = ZonedDateTime.of(LocalDate.of(2013, 10, 27), LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        System.out.println("ambiguous = " + ambiguous);//ambiguous = 2013-10-27T02:30+02:00[Europe/Berlin]
        ZonedDateTime anHourLater = ZonedDateTime.of(LocalDate.of(2013, 10, 27), LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        System.out.println("anHourLater = " + anHourLater);//anHourLater = 2013-10-27T02:30+02:00[Europe/Berlin]

        //美国夏令时，2013.3.10-2013.11.3
        ZonedDateTime meeting = ZonedDateTime.of(LocalDate.of(2013, 10, 31), LocalTime.of(14, 30), ZoneId.of("America/Los_Angeles"));
        System.out.println("meeting = " + meeting);//meeting = 2013-10-31T14:30-07:00[America/Los_Angeles]

        ZonedDateTime nextMeeting = meeting.plus(Duration.ofDays(7));
        System.out.println("nextMeeting = " + nextMeeting);//nextMeeting = 2013-11-07T13:30-08:00[America/Los_Angeles]
        nextMeeting = meeting.plus(Period.ofDays(7));
        System.out.println("nextMeeting = " + nextMeeting);//nextMeeting = 2013-11-07T14:30-08:00[America/Los_Angeles]

    }
}
