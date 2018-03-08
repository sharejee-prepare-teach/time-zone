package develop;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

/**
 * Created by : Ron Rith
 * Create Date: 03/07/2018.
 */
public class CoreZonedDateTime {
    private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";

    public static void main(String[] args) {

        //getSampleTimeZone();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        String amPM = getRealTimeWithAMPM(localTime);
        String khmerTime = getKhmerRealTime(localTime);
        String khmerDate = getRealTimeWithFormat(localDate);
        String realDateTime = getKhmerRealDateTime(khmerDate,khmerTime,amPM);

        //System.out.println(realDateTime);

        //getSampleTimeZone(realDateTime);
        //getKhmerDateTimeZone(realDateTime);
        System.out.println("Return Khmer AM PM => " + amPM);
        System.out.println("Return Khmer Time => " + khmerTime);
        System.out.println("Return Khmer Date => " + khmerDate);
        System.out.println("Return Khmer Date Time => " + getReturnKhmerDateTimeZone(realDateTime));
    }
    public static void getSampleTimeZone(String dateInString){
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

    public static String getKhmerRealDateTime(String localDate,String localTime,String time){
        String localDateTime = localDate + " " + localTime + " "+ time;
        return localDateTime;
    }

    public static String getRealTimeWithAMPM(LocalTime time){
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
    public static String getKhmerRealTime(LocalTime time){
        String khmerTime = "";

        StringTokenizer stringTokenizer = new StringTokenizer(String.valueOf(time),".");
        khmerTime = stringTokenizer.nextToken();
        return khmerTime;
    }


    public static String getRealTimeWithFormat(LocalDate date){
        String format = "";

        StringTokenizer stringTokenizer = new StringTokenizer(String.valueOf(date), "-");
        CoreDateFormat dateFormat = new CoreDateFormat();
        while (stringTokenizer.hasMoreTokens()) {
            dateFormat.setYear(stringTokenizer.nextToken());
            dateFormat.setMonth(stringTokenizer.nextToken());
            dateFormat.setDay(stringTokenizer.nextToken());
        }
        format = dateFormat.getDay() + "-" + dateFormat.getMonth() + "-" + dateFormat.getYear();
        return format;
    }

    public static void getKhmerDateTimeZone(String dateInString){
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

    public static String getReturnKhmerDateTimeZone(String dateInString){
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));

        ZoneId singaporeZoneId = ZoneId.of("Asia/Singapore");

        //LocalDateTime + ZoneId = ZonedDateTime
        ZonedDateTime asiaZonedDateTime = ldt.atZone(singaporeZoneId);

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return String.valueOf(format.format(asiaZonedDateTime));
    }
}



