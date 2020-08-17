package com.zentagroup.birthdaycountdown.util;

import com.zentagroup.birthdaycountdown.exception.InvalidDateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 * Ariana González
 * ariana.sgm5@gmail.com
 */

/**
 * Class containing various reusable date related methods and utilities
 */
@Service
public class DateUtils {

    public DateUtils() {
    }

    /**
     * Returns the number of days between two months of the same year by adding
     * the days in each month from the earliest one to the latest one and finally
     * subtracting the earliest month's passed days and the latest month's
     * days not yet passed
     * Counts to, but not including the latest date.
     * @param lmonth latest month
     * @param emonth earliest month
     * @param year year of both months
     * @param lday day of latest date
     * @param eday day of earliest date
     * @return int number of days between month-day dates
     * @throws InvalidDateException if earliest date is different to and after latest date
     */
    public int getDaysBetweenMonths(int lmonth, int emonth, int year, int lday, int eday) throws InvalidDateException{
        this.earliestDateSameOrBeforeLatestDate(
                LocalDate.of(year, lmonth, lday == 0? 1 : lday), LocalDate.of(year, emonth, eday == 0? 1 : eday));
        int resp = 0;
        for(int i = lmonth; i >= emonth; i--){
            resp += this.getDaysInMonth(i, year);
        }
        if(eday != 0) resp -= eday;
        if(lday != 0) resp -= this.getDaysInMonth(lmonth, year) - lday;
        return resp;
    }

    /**
     * Returns the number of days until next date and month coincidence of a given date.
     * If the month and day are the same it returns 0. If the date to match was earlier
     * in the current month or in a different month it adds the days between the months using
     * the getDaysBetweenMonths method. If the date to match is the next year it calculates
     * first from the current month until december and then from january until the date to match
     * and adds them.
     * @param nextCoincidence date with next day and month coincidence to be matched
     * @param startingDate start of period
     * @return int number of days until next date month coincidence
     * @throws InvalidDateException if earliest date is different to and after latest date
     */
    public int getDaysUntilNextMatchingDate(LocalDate nextCoincidence, LocalDate startingDate) throws InvalidDateException{
        LocalDate start = startingDate;
        this.earliestDateSameOrBeforeLatestDate(start, nextCoincidence);
        int days = nextCoincidence.getDayOfMonth() - start.getDayOfMonth();
        int months = nextCoincidence.getMonthValue() - start.getMonthValue();
        if(months == 0){
            if(days == 0) return 0;
            if(days > 0) return days;
        }
        if(months > 0) {
            return getDaysBetweenMonths( nextCoincidence.getMonthValue(), start.getMonthValue(),
                    start.getYear(), nextCoincidence.getDayOfMonth(), start.getDayOfMonth());
        }
        return getDaysBetweenMonths(12, start.getMonthValue(), start.getYear(),
                0, start.getDayOfMonth()) + getDaysBetweenMonths(nextCoincidence.getMonthValue(), 1,
                start.getYear()+1, nextCoincidence.getDayOfMonth(), 0);
    }

    /**
     * Checks whether a year is a leap year. Returns true
     * if it is a leap year, otherwise it returns false.
     * @param year int year to be checked
     * @return boolean true if is leap year
     */
    public boolean isLeapYear(int year){
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    /**
     * Returns the number of days in a month. If the month is
     * february it uses the isLeapYear method to return
     * the right number of days.
     * @param monthValue int corresponding to the month
     * @param year int corresponding to the months year
     * @return int number of days in the month
     */
    public int getDaysInMonth (int monthValue, int year){
        List months30days = Arrays.asList(4, 6, 9, 11);
        if(monthValue == 2) return isLeapYear(year)? 29 : 28;
        if(months30days.contains(monthValue)) return 30;
        return 31;
    }

    /**
     * Returns the number of years between two dates.
     * @param earliestDate LocalDate
     * @param latestDate LocalDate
     * @return long number of years
     * @throws InvalidDateException if earliest date is different to and after latest date
     */
    public long getYearsBetweenDates(LocalDate earliestDate, LocalDate latestDate) throws InvalidDateException{
        this.earliestDateSameOrBeforeLatestDate(latestDate, earliestDate);
        return ChronoUnit.YEARS.between(earliestDate, latestDate);
    }

    /**
     * Returns a String with the years, months and days between two given dates
     * @param earliestDate LocalDate
     * @param latestDate LocalDate
     * @return String written time between dates
     * @throws InvalidDateException if earliest date is different to and after latest date
     */
    public String getAgeText(LocalDate earliestDate, LocalDate latestDate) throws InvalidDateException{
        this.earliestDateSameOrBeforeLatestDate(latestDate, earliestDate);
        int years = latestDate.getYear() - earliestDate.getYear();
        int months = 0;
        int days = 0;
        if(earliestDate.getMonthValue() > latestDate.getMonthValue()){
            if(years != 0) years--;
            months = 12 - (earliestDate.getMonthValue() - latestDate.getMonthValue());
        }
        if(earliestDate.getMonthValue() < latestDate.getMonthValue()){
            months = latestDate.getMonthValue() - earliestDate.getMonthValue();
        }
        if(earliestDate.getDayOfMonth() > latestDate.getDayOfMonth()){
            if(months != 0) months --;
            days = getDaysInMonth(latestDate.getMonthValue(), latestDate.getYear()) - (earliestDate.getDayOfMonth() - latestDate.getDayOfMonth());
        }
        if(earliestDate.getDayOfMonth() < latestDate.getDayOfMonth()) {
            days = latestDate.getDayOfMonth() - earliestDate.getDayOfMonth();
            if(days == 30){ months++; days = 0; }
        }
        return turnTimePeriodIntoText(years, months, days);
    }


    /**
     * Receives a number of years, months and dates and turns it into a text,
     * If one of the values equals 0 it isn't included in the text.
     * @param years number of years
     * @param months number of months
     * @param days number of days
     * @return textual representation of time period
     */
    public String turnTimePeriodIntoText(int years, int months, int days){
        String text = "";
        text += years == 0? "" : years == 1? "1 año " : years + " años ";
        text += days == 0 && months > 0? "y " : "";
        text += months == 0? "" : months == 1? "1 mes " : months + " meses ";
        text += (years > 0 || months > 0) && days > 0? "y " : "";
        text += days == 0? "" : days == 1? "1 día " : days + " días";
        return text;
    }

    /**
     * Checks whether the earliest date is the same or before the lastest date.
     * If none of those conditions are met it throws an exception.
     * @param latestDate date to be checked as latest
     * @param earliestDate date be checked as earliest
     * @throws InvalidDateException if earliest date is different to and after latest date
     */
    public void earliestDateSameOrBeforeLatestDate(LocalDate latestDate, LocalDate earliestDate) throws InvalidDateException{
        if(earliestDate.isAfter(latestDate) && !earliestDate.equals(latestDate))
            throw new InvalidDateException("Invalid date operation: The latest date can't be before the earliest date");
    }
}
