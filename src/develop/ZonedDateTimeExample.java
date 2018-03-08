package develop;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

/**
 * Created by : Ron Rith
 * Create Date: 03/07/2018.
 */
public class ZonedDateTimeExample {
    private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";

    public static void main(String[] args) {

        //getSampleTimeZone();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        String time = getRealTimeWithAMPM(localTime);
        String date = getRealTimeWithFormat(localDate);
        String realDateTime = getRealDateTime(date,localTime,time);

        System.out.println(realDateTime);

        getSampleTimeZone(realDateTime);
        //getKhmerDateTimeZone(realDateTime);
    }
    private static void getSampleTimeZone(String dateInString){
        //String dateInString = "22-1-2015 10:15:55 AM";
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));

        ZoneId singaporeZoneId = ZoneId.of("Asia/Singapore");
        System.out.println("TimeZone : " + singaporeZoneId);

        //LocalDateTime + ZoneId = ZonedDateTime
        ZonedDateTime asiaZonedDateTime = ldt.atZone(singaporeZoneId);
        System.out.println("Date (Singapore) : " + asiaZonedDateTime);

        ZoneId newYokZoneId = ZoneId.of("America/New_York");
        System.out.println("TimeZone : " + newYokZoneId);

        ZonedDateTime nyDateTime = asiaZonedDateTime.withZoneSameInstant(newYokZoneId);
        System.out.println("Date (New York) : " + nyDateTime);

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        System.out.println("\n---DateTimeFormatter---");
        System.out.println("Date (Singapore) : " + format.format(asiaZonedDateTime));
        System.out.println("Date (New York) : " + format.format(nyDateTime));
    }

    private static String getRealDateTime(String localDate,LocalTime localTime,String time){
        String localDateTime = localDate + " " + localTime;

        //cute dot(.)
        StringTokenizer stringTokenizer = new StringTokenizer(String.valueOf(localDateTime),".");
        localDateTime = stringTokenizer.nextToken();
        return localDateTime + " "+time;
    }

    private static String getRealTimeWithAMPM(LocalTime time){
        String amPM = "";

        StringTokenizer stringTokenizer = new StringTokenizer(String.valueOf(time),":");
        amPM = stringTokenizer.nextToken();
        Integer hours = Integer.valueOf(amPM);
        if(hours<=12){
            return "AM";
        }else {
            return "PM";
        }
    }

    private static String getRealTimeWithFormat(LocalDate date){
        String format = "";

        StringTokenizer stringTokenizer = new StringTokenizer(String.valueOf(date), "-");
        DateFormat dateFormat = new DateFormat();
        while (stringTokenizer.hasMoreTokens()) {
            dateFormat.setYear(stringTokenizer.nextToken());
            dateFormat.setMonth(stringTokenizer.nextToken());
            dateFormat.setDay(stringTokenizer.nextToken());
        }
        format = dateFormat.getDay() + "-" + dateFormat.getMonth() + "-" + dateFormat.getYear();
        return format;
    }

    private static void getKhmerDateTimeZone(String dateInString){
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));

        ZoneId singaporeZoneId = ZoneId.of("Asia/Singapore");
        System.out.println("TimeZone : " + singaporeZoneId);

        //LocalDateTime + ZoneId = ZonedDateTime
        ZonedDateTime asiaZonedDateTime = ldt.atZone(singaporeZoneId);
        System.out.println("Date (Singapore) : " + asiaZonedDateTime);

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        System.out.println("\n---DateTimeFormatter---");
        System.out.println("Date (Singapore) : " + format.format(asiaZonedDateTime));
    }
}



