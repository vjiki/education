package com.epam.lab;

import com.epam.lab.service.DateService;
import com.epam.lab.service.SimpleDateService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DateServiceTest {

    private static DateService dateService;

    @BeforeAll
    static void setup() {
        dateService = new SimpleDateService();
    }

    @Test
    void testParseDate() {
        assertEquals("30-01-2020", dateService.parseDate(LocalDate.of(2020, 1, 30)),
                "Для вызова метода: dateService.parseDate(LocalDate.of(2020, 1, 30))");
    }

    @Test
    void testParseString() {
        assertEquals(of(1970, 1, 1).atStartOfDay(), dateService.parseString("1970-01-01 00:00"),
                "Для вызова метода: dateService.parseString(\"1970-01-01 00:00\")");
    }


    @Test
    void testConvertToAnotherFormat() {
        assertEquals("2011-12-03",
                dateService.convertToCustomFormat(LocalDate.of(2011, 12, 3), DateTimeFormatter.ISO_LOCAL_DATE),
                "Для вызова метода: dateService.convertToCustomFormat(LocalDate.of(2011, 12, 3), DateTimeFormatter.ISO_LOCAL_DATE)");
    }

    @Test
    void testGetNextLeapYear() {
        assertEquals(2024, dateService.getNextLeapYear(),
                "Для вызова метода: dateService.getNextLeapYear()");
    }

    @Test
    void testGetSecondsInYear() {
        assertEquals(31622400, dateService.getSecondsInYear(2020),
                "Для вызова метода: dateService.getSecondsInYear(2020)");
    }

}
