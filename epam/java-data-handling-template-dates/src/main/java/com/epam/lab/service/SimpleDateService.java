package com.epam.lab.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class SimpleDateService implements DateService {

    /**
     * should parse date into string
     *
     * @param localDate date
     * @return formatted string day-month-year(01-01-1970)
     */
    @Override
    public String parseDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * should parse string into date
     *
     * @param string formatted string year-month-day hour:minute (1970-01-01 00:00)
     * @return date and time
     */
    @Override
    public LocalDateTime parseString(String string) {
        return LocalDateTime.parse(string,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * converts date into string with specified formatter
     *
     * @param localDate initial date
     * @param formatter formatter
     * @return resulting string
     */
    @Override
    public String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    /**
     * should return the next leap year
     *
     * @return the next leap year
     */
    @Override
    public long getNextLeapYear() {
        long year = LocalDate.now().getYear();
        while(!Year.isLeap(year)) {
            year++;
        }
        return year;
    }

    /**
     * counts number of seconds in year
     *
     * @return number of seconds in year
     */
    @Override
    public long getSecondsInYear(int year) {
        return Year.of(year).length()* 24 * 60 * 60;
    }


}
