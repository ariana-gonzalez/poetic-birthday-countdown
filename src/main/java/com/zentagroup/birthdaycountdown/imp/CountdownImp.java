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
     *
     * @param names
     * @param lastnames
     * @param birthDate
     * @return
     * @throws Exception
     */
    public RespCountdownDto<Object> getBirthdayCountdown(String names, String lastnames, LocalDate birthDate) throws Exception{
        RespCountdownDto<Object> resp = new RespCountdownDto();
        try{
            resp.setAge((int)dateU.getYearsBetweenDates(birthDate, LocalDate.now()));
            resp.setName(this.getFirstWord(names) + ' ' + this.getFirstWord(lastnames));
            resp.setAgeText(dateU.getAgeText(birthDate, LocalDate.now()));
            int days = dateU.getDaysUntilBirthday(birthDate);
            if (days == 0) {
                resp.setCountdownResult(poemImp.getRandomPoem(poemImp.getPoems()));
            } else {
                resp.setCountdownResult(days);
            }
        }catch(InvalidBirthDateException ex){
            throw new InvalidBirthDateException(ex.getMessage());
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
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
