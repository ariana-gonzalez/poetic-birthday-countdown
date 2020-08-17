package com.zentagroup.birthdaycountdown.util;

import static org.junit.jupiter.api.Assertions.*;
import com.zentagroup.birthdaycountdown.exception.InvalidDateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.time.LocalDate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DateUtilsTest {
    public final static LocalDate EARLIEST_DATE = LocalDate.of(2019, 8, 15);
    public final static LocalDate LATEST_DATE = LocalDate.of(2020, 8, 15);

    private DateUtils du = new DateUtils();

    @Test
    @DisplayName("Test getting months between two dates")
    void testGetDaysBetweenMonths() throws Exception {
        // Same month different days
        assertEquals(30, du.getDaysBetweenMonths(12, 12, 2020, 31, 1));
        // Leap year should return 365 between first and last day of the year
        assertEquals(365, du.getDaysBetweenMonths(12, 1, 2020, 31, 1));
        // Not a leap year should return 364 between first and last day of the year
        assertEquals(364, du.getDaysBetweenMonths(12, 1, 2019, 31, 1));
        // Same date
        assertEquals(0, du.getDaysBetweenMonths(12, 12, 2019, 31, 31));
        // Day after
        assertEquals(1, du.getDaysBetweenMonths(12, 12, 2019, 31, 30));
        // Earliest month after latest month
        assertThrows(InvalidDateException.class, () -> du.getDaysBetweenMonths(1, 12, 2020, 30, 1));
    }

    @Test
    @DisplayName("Test getting days until next matching day and month from another date")
    void testGetDaysUntilNextMatchingDate() throws InvalidDateException{
        // When same day and month then return 0
        assertEquals(0, du.getDaysUntilNextMatchingDate(EARLIEST_DATE, LATEST_DATE));
        // Start date same month one day before matching date
        assertEquals(1, du.getDaysUntilNextMatchingDate(EARLIEST_DATE, LocalDate.of(2020, 8, 14)));
        // Start date same month one day after matching date
        assertEquals(364, du.getDaysUntilNextMatchingDate(EARLIEST_DATE, LocalDate.of(2020, 8, 16)));
    }

    @Test
    @DisplayName("Test checking if a year is leap year")
    void testIsLeapYear(){
        // When year not divisible by 4 then isn't leap
        assertFalse(du.isLeapYear(2001));
        // When year divisible by 4 but not by 100 then is leap
        assertTrue(du.isLeapYear(1992));
        // When year divisible 4, 100 and 400 then is leap
        assertTrue(du.isLeapYear(1600));
        // When year divisible 4, 100 but not by 400 isn't leap
        assertFalse(du.isLeapYear(1900));

    }

    @Test
    @DisplayName("Test getting days in a month")
    void testGetDaysInMonth(){
        // When February leap year then return 29
        assertEquals(29, du.getDaysInMonth(2, 2020));
        // When February not a leap year then return 28
        assertEquals(28, du.getDaysInMonth(2, 2019));
        // When 30 day month then return 30
        assertEquals(30, du.getDaysInMonth(4, 2019));
        // When 31 day month then return 31
        assertEquals(31, du.getDaysInMonth(12, 2019));
    }

    @Test
    @DisplayName("Test getting years between dates")
    void testGetYearsBetweenDates() throws InvalidDateException{
        // When same date then return 0
        assertEquals(0, du.getYearsBetweenDates(EARLIEST_DATE, EARLIEST_DATE));
        // When less than a year between dates return 0
        assertEquals(0, du.getYearsBetweenDates(EARLIEST_DATE, LocalDate.of(2019, 10, 15)));
        // When more than a year but less than two years between dates return 1
        assertEquals(1, du.getYearsBetweenDates(EARLIEST_DATE, LocalDate.of(2021, 02, 15)));
        // When earliest date before latest date then return year difference between dates
        assertEquals(1, du.getYearsBetweenDates(EARLIEST_DATE, LATEST_DATE));
        // When earliest date after latest date then throw exception
        assertThrows(InvalidDateException.class, () -> du.getYearsBetweenDates(LATEST_DATE, EARLIEST_DATE));
    }

    @Test
    @DisplayName("Test turning time period into text")
    void testTurnTimePeriodIntoText() throws InvalidDateException{
        // When none equal 0 then return complete text
        String regexDMY = "(0?[1-9]{1,2}[0]?|100)\\b ((\\baño\\b)|(\\baños\\b)) (0?[1-9]|10|11) ((\\bmes\\b)|(\\bmeses\\b)) \\by\\b (0?[1-9]|1[0-9]|2[0-9]) ((\\bdía\\b)|(\\bdías\\b))$";
        assertTrue(du.turnTimePeriodIntoText(2, 2, 5).matches(regexDMY));
        assertTrue(du.turnTimePeriodIntoText(1, 11, 6).matches(regexDMY));
        // When all equal 0 then return empty string
        assertEquals("", du.turnTimePeriodIntoText(0, 0, 0));
        // When month equals 0 then return text without months
        String regexNoMonth = "(0?[1-9]{1,2}[0]?|100)\\b ((\\baño\\b)|(\\baños\\b)) \\by\\b (0?[1-9]|1[0-9]|2[0-9]) ((\\bdía\\b)|(\\bdías\\b))$";
        assertTrue(du.turnTimePeriodIntoText(1, 0, 6).matches(regexNoMonth));
        // When year equals 0 then return text without years
        String regexNoYear = "(0?[1-9]|10|11) ((\\bmes\\b)|(\\bmeses\\b)) \\by\\b (0?[1-9]|1[0-9]|2[0-9]) ((\\bdía\\b)|(\\bdías\\b))$";
        assertTrue(du.turnTimePeriodIntoText(0, 5, 6).matches(regexNoYear));
        // When day equals 0 then return text without days
        String regexNoDay = "(0?[1-9]{1,2}[0]?|100)\\b ((\\baño\\b)|(\\baños\\b)) \\by\\b (0?[1-9]|10|11) ((\\bmes\\b)|(\\bmeses\\b)) ";
        assertTrue(du.turnTimePeriodIntoText(1, 6, 0).matches(regexNoDay));
    }

    @Test
    @DisplayName("Test getting age text with years, months and days")
    void testGetAgeTest() throws InvalidDateException{
        // When year, month and day difference then contains seven words (Ex: 1 año 2 meses y 4 días)
        assertEquals(7, du.getAgeText(EARLIEST_DATE, LocalDate.of(2022, 03, 2)).split("\\s").length);
        // When only year and month difference (Ex: 1 año y 2 meses)
        assertEquals(5, du.getAgeText(EARLIEST_DATE, LocalDate.of(2022, 03, 15)).split("\\s").length);
        // When only year difference then it contains only two words (Ex: 3 años)
        assertEquals(2, du.getAgeText(EARLIEST_DATE, LATEST_DATE).split("\\s").length);
        // When month and day difference (Ex: 2 meses y 2 días)
        assertEquals(5, du.getAgeText(EARLIEST_DATE, LocalDate.of(2019, 11, 1)).split("\\s").length);
        // When latest date before arliest date then throw Exception
        assertThrows(InvalidDateException.class, () -> du.getAgeText(EARLIEST_DATE, LocalDate.of(2019, 03, 1)));
    }
}
