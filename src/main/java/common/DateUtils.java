package common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getFutureDate(int days) {
        LocalDate futureDate = LocalDate.now().plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return futureDate.format(formatter);
    }
}
