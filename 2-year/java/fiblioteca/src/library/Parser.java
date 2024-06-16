package library;

import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Parser {
  public static int parseInt(String value) {
    return Integer.parseInt(value);
  }

  public static String parseStr(String value) {
      return String.valueOf(value);
  }

  public static LocalDate parseLocalDate(String value) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    return LocalDate.parse(value, formatter);
  }

  public static LocalDate parseLocalDate(Date value) {
    long milliseconds = value.getTime();

    Instant instant = Instant.ofEpochMilli(milliseconds);

    return instant.atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static Date parseDate(LocalDate value) {
      return Date.valueOf(value);
  }
}
