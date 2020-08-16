package com.zentagroup.birthdaycountdown.imp;

import com.zentagroup.birthdaycountdown.exception.InvalidBirthDateException;
import com.zentagroup.birthdaycountdown.dto.RespCountdownDto;
import com.zentagroup.birthdaycountdown.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

/**
 * Ariana González
 * ariana.sgm5@gmail.com
 */

@Service
public class CountdownImp {
    @Autowired
    private DateUtils dateU;

    @Autowired
    private PoemImp poemImp;

    /**
     * Wrapper class for getBirthdayCountdownForDates with the current date as the custom date
     * @param names
     * @param lastnames
     * @param birthDate date of birth
     * @return RespCountdownDto<Object>
     * @throws Exception
     */
    public RespCountdownDto<Object> getBirthdayCountdown(String names, String lastnames,
                                                         LocalDate birthDate) throws Exception{
        return getBirthdayCountdownForDates(names, lastnames, birthDate, LocalDate.now());
    }

    /**
     * Based on a custom date it returns an object with the name, age in years, age in text
     * including months and days, and days until birthday or a random poem if
     * the birthday is the same date as the custom date.
     * @param names
     * @param lastnames
     * @param birthDate date of birth
     * @param dateToCheck custom date to base calculations
     * @return RespCountdownDto<Object>
     * @throws Exception
     */
    public RespCountdownDto<Object> getBirthdayCountdownForDates(String names, String lastnames,
                                                                 LocalDate birthDate, LocalDate dateToCheck) throws Exception{
        RespCountdownDto<Object> resp = new RespCountdownDto();
        resp.setAge((int)dateU.getYearsBetweenDates(birthDate, dateToCheck));
        resp.setName(this.getFirstWord(names) + ' ' + this.getFirstWord(lastnames));
        resp.setAgeText(dateU.getAgeText(birthDate, dateToCheck));
        int days = dateU.getDaysUntilNextMatchingDate(birthDate, dateToCheck);
        if (days == 0) {
            resp.setCountdownResult(poemImp.getRandomPoem(poemImp.getPoems()));
        } else {
            resp.setCountdownResult(days);
        }
        return resp;
    }

    /**
     * Returns the first word of a string separating it from the rest
     * by the first space. If there's only one word it returns it.
     * @param text string which's first work will be returned
     * @return String first word
     */
    public String getFirstWord(String text) {
        int index = text.indexOf(' ');
        if (index > -1) return text.substring(0, index).trim();
        return text;
    }
}
