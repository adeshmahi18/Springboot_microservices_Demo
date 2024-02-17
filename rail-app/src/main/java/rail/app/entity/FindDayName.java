package rail.app.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class FindDayName {
    public static void main(String[] args) {
        String dateString = "2024-02-21T18:30:00.000Z";

        // Parse the date string to Instant
        Instant instant = Instant.parse(dateString);

        // Get the day of the week
        DayOfWeek dayOfWeek = instant.atZone(ZoneId.systemDefault()).getDayOfWeek();

        // Get the day name
        String dayName = dayOfWeek.toString();

        System.out.println("Day Name: " + dayName);
    }
}
