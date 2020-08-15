package com.zentagroup.birthdaycountdown.util;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Ariana González
 * ariana.sgm5@gmail.com
 */


/**
 * Class containing various reusable date related methods and utilities
 */
@Service
public class DateUtils {

    /**
     * Returns the number of days between two months of the same year by adding
     * the days in each month from the earliest one to the latest one and finally
     * subtracting the earliest month's passed days and the latest month's
     * days not yet passed
     * @param lmonth latest month
     * @param emonth earliest month
     * @param year year of both months
     * @param lday day of latest date
     * @param eday day of earliest date
     * @return int number of days between month-day dates
     */
    public int getDaysBetweenMonths(int lmonth, int emonth, int year, int lday, int eday){
        int resp = 0;
        for(int i = lmonth; i >= emonth; i--){
            resp += this.getDaysInMonth(i, year);
        }
        if(eday != 0) resp -= eday;
        if(lday != 0) resp -= this.getDaysInMonth(lmonth, year) - lday;
        return resp;
    }

    /**
     * Returns the number of days until next birthday. If the month and day
     * are the same it returns 0. If the birth date day was earlier in the current
     * month or in a different month it adds the days between the months using
     * the getDaysBetweenMonths method. If the birthday is the next year it calculates
     * first from the current month until december and then from january until the birthday
     * date and adds them.
     * @param bd birth date
     * @return int number of days until birthday
     */
    public int getDaysUntilBirthday(LocalDate bd){
        LocalDate today = LocalDate.now();
        int days = bd.getDayOfMonth() - today.getDayOfMonth();
        int months = bd.getMonthValue() - today.getMonthValue();
        if(months == 0){
            if(days == 0) return 0;
            if(days > 0) return days;
        }
        if(months > 0) {
            return getDaysBetweenMonths( bd.getMonthValue(), today.getMonthValue(),
                    today.getYear(), bd.getDayOfMonth(), today.getDayOfMonth());
        }
        return getDaysBetweenMonths(12, today.getMonthValue(), today.getYear(),
                0, today.getDayOfMonth()) + getDaysBetweenMonths(bd.getMonthValue(), 1,
                today.getYear()+1, bd.getDayOfMonth(), 0);
    }

    /**
     * Checks whether a year is a leap year. Returns true
     * if it is a leap year, otherwise it returns false.
     * @param year int year to be checked
     * @return boolean
     */
    public boolean isLeapYear(int year){
        if(year % 4 == 0){
            if(year % 100 == 0)
                if (year % 400 == 0)
                    return true;
            else
                return true;
        }
        return false;
    }

    /**
     * Returns the number of days in a month. If the month is
     * february it uses the isLeapYear method to return
     * the right number of days.
     * @param monthValue int corresponding to the month
     * @param year int corresponding to the months year
     * @return int number of days in the month
     */
    public int getDaysInMonth(int monthValue, int year){
        switch (monthValue){
            case 2:
                return isLeapYear(year)? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    public int getDaysInMonth2(int monthValue, int year){
        if(monthValue == 2) return isLeapYear(year)? 29 : 28;
        if(monthValue == 4 || monthValue == 6 || monthValue == 9 || monthValue == 11) return 30;
        return 31;
    }

    /**
     * Returns the number of years between two dates.
     * @param earliestDate LocalDate
     * @param latestDate LocalDate
     * @return long number of years
     */
    public long getYearsBetweenDates(LocalDate earliestDate, LocalDate latestDate){
        return ChronoUnit.YEARS.between(earliestDate, latestDate);
    }

    /**
     *

        public String getYearMonthDayBetweenDates(LocalDate earliestDate, LocalDate latestDate) throws Exception{
            if(earliestDate.isAfter(latestDate)) throw new Exception();
            if(earliestDate.getYear() < latestDate.getYear())
                return (latestDate.getYear() - earliestDate.getYear()) + " años";
            if(earliestDate.getMonthValue() < latestDate.getMonthValue())
                return latestDate.getMonthValue() - earliestDate.getMonthValue() + " meses";
            if(earliestDate.getDayOfMonth() < latestDate.getDayOfMonth())
                return latestDate.getDayOfMonth() - earliestDate.getDayOfMonth() + " días";
            return "cumpleaños";
        }
     *
     */

    /**
     * Returns a String with the years, months and days between two given dates
     * @param earliestDate LocalDate
     * @param latestDate LocalDate
     * @return String written time between dates
     */
    public String getAgeText(LocalDate earliestDate, LocalDate latestDate){
        int years = 0;
        int months = 0;
        int days = 0;
        years = latestDate.getYear() - earliestDate.getYear();
        if(earliestDate.getMonthValue() > latestDate.getMonthValue()){
            if(years != 0) years--;
            months = 12 - (earliestDate.getMonthValue() - latestDate.getMonthValue());
        }
        if(earliestDate.getMonthValue() < latestDate.getMonthValue()){
            months = latestDate.getMonthValue() - earliestDate.getMonthValue();
        }
        if(earliestDate.getDayOfMonth() -1 > latestDate.getDayOfMonth()){
            if(months != 0) months --;
            days = getDaysInMonth(latestDate.getMonthValue(), latestDate.getYear()) - (earliestDate.getDayOfMonth() - latestDate.getDayOfMonth());
        }
        if(earliestDate.getDayOfMonth() < latestDate.getDayOfMonth()) {
            days = latestDate.getDayOfMonth() - earliestDate.getDayOfMonth() +1;
        }
        return years + " años " + months + " meses " + days + " días";
    }
}
