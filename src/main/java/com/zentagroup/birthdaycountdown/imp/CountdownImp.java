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
     * Returns an object with the name, age in years, age in text
     * including months and days, and days until birthday or a random poem if
     * the birthday is the same date as the current date.
     * @param names
     * @param lastnames
     * @param birthDate date of birth
     * @return RespCountdownDto<Object>
     * @throws Exception
     */
    public RespCountdownDto<Object> getBirthdayCountdown(String names, String lastnames, LocalDate birthDate) throws Exception{
        RespCountdownDto<Object> resp = new RespCountdownDto();
        resp.setAge((int)dateU.getYearsBetweenDates(birthDate, LocalDate.now()));
        resp.setName(this.getFirstWord(names) + ' ' + this.getFirstWord(lastnames));
        resp.setAgeText(dateU.getAgeText(birthDate, LocalDate.now()));
        int days = dateU.getDaysUntilNextMatchingDate(birthDate, LocalDate.now());
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
    private String getFirstWord(String text) {
        int index = text.indexOf(' ');
        if (index > -1) return text.substring(0, index).trim();
        return text;
    }
}
