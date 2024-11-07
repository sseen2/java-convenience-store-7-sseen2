package store;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {
    private static final String PARSING_PATTERN = "yyyy-MM-dd";

    public static LocalDate parseDate(String dateString) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(PARSING_PATTERN);
            return LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
