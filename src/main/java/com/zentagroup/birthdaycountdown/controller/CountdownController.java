package com.zentagroup.birthdaycountdown.controller;

import com.zentagroup.birthdaycountdown.exception.InvalidBirthDateException;
import com.zentagroup.birthdaycountdown.imp.CountdownImp;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import static com.zentagroup.birthdaycountdown.util.Constant.ERROR_INVALID_DATA;


/**
 * Ariana González
 * ariana.sgm5@gmail.com
 */

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/v1/poetic-birthday")
public class CountdownController {
    @Autowired
    private CountdownImp countdownImp;

    @GetMapping("/countdown")
    public ResponseEntity<Object> getBirthdayCountdown(@RequestParam String names,
                                                       @RequestParam String lastnames,
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        ResponseEntity<Object> rs = null;
        try {
            this.checkRequestValidity(names, lastnames, date);
            rs = new ResponseEntity<Object>(countdownImp.getBirthdayCountdown(names, lastnames, date), HttpStatus.OK) ;
        }catch (InvalidBirthDateException | NullPointerException ex) {
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
        return  rs;
    }


    /**
     * Validates the birthday countdown request fields so that they are not empty or invalid.
     * If they are invalid it returns an exception.
     * @param names
     * @param lastnames
     * @param date date of birth
     * @throws Exception
     */
    public void checkRequestValidity(String names, String lastnames, LocalDate date) throws Exception{
        if(StringUtils.isBlank(names) || StringUtils.isBlank(lastnames)){
            throw new NullPointerException(ERROR_INVALID_DATA);
        }
        this.checkBirthDateValidity(date);

    }

    /**
     * Checks whether the given birth date is after the current date
     * which makes it invalid. If that's the case it throws a custom exception.
     * @param birthDate date of birth
     * @throws InvalidBirthDateException when the date of birth is future
     */
    public void checkBirthDateValidity(LocalDate birthDate) throws InvalidBirthDateException {
        LocalDate today = LocalDate.now();
        if(birthDate.isAfter(today))
            throw new InvalidBirthDateException(ERROR_INVALID_DATA);
    }
}
