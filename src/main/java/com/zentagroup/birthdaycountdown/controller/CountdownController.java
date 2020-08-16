package com.zentagroup.birthdaycountdown.controller;

import com.zentagroup.birthdaycountdown.exception.InvalidBirthDateException;
import com.zentagroup.birthdaycountdown.exception.InvalidDateException;
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

    /**
     * Get method that receives names, lastnames and a date of birth checks the values are
     * valid and returns a ResponseEntity with the first name and lastname, age and days
     * until birthday or a poem if it's the persons birthday. If an error occurs it handles
     * the exception and returns the error message and a suitable error HttpStatus.
     * @param names name or names
     * @param lastnames lastname or lastnames
     * @param birthDate LocalDate must be previous to the current date to be valid
     * @return ResponseEntity<Object>
     */
    @GetMapping("/countdown")
    public ResponseEntity<Object> getBirthdayCountdown(@RequestParam String names,
                                                       @RequestParam String lastnames,
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate) {
        try {
            this.checkRequestValidity(names, lastnames, birthDate);
            return new ResponseEntity<Object>(countdownImp.getBirthdayCountdown(names, lastnames, birthDate), HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
            return handleException(ex);
        }
    }


    /**
     * Validates the birthday countdown request fields so that they are not empty or invalid.
     * If they are invalid it returns an exception.
     * @param names
     * @param lastnames
     * @param date date of birth
     * @throws Exception
     */
    private void checkRequestValidity(String names, String lastnames, LocalDate date) throws Exception{
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
    private void checkBirthDateValidity(LocalDate birthDate) throws InvalidBirthDateException {
        LocalDate today = LocalDate.now();
        if(birthDate.isAfter(today))
            throw new InvalidBirthDateException(ERROR_INVALID_DATA);
    }

    /**
     * Handles a given exception and returns a ResponseEntity with
     * a message and status suitable for the specific error.
     * @param ex exception to handle
     * @return
     */
    private ResponseEntity<Object> handleException(Exception ex){
        /**
         * try {
         *    throw ex;
         * }catch (InvalidBirthDateException | NullPointerException exc) {
         *    return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
         * } catch (Exception exc){
         *    return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
         * }
         *
         */
        if(ex instanceof InvalidDateException | ex instanceof NullPointerException){
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
