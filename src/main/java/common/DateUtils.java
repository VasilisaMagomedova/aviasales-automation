package common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

    public static String getFutureDate(int days) {
        LocalDate futureDate = LocalDate.now().plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        return futureDate.format(formatter);
    }

    // метод приводит дату к одному формату
    public static String normalizeDate(String date) {
        // заменяем на обычные пробелы, убираем "я" в конце месяца если можно, берем первые 3 символа
        return date.replace("\u00A0", " ")
                .replaceAll("(январ|феврал|март|апрел|июн|июл|август|сентябр|октябр|ноябр|декабр)я?", "$1")
                .replaceAll("(.{3}).*", "$1");
    }

}
