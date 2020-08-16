package com.zentagroup.birthdaycountdown.controller;

import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;

//@RunWith()
public class CountdownControllerTest {

    // Testing getBirthdayCountdown
    @Test
    void whenValidInput_thenReturns200(){

    }

    @Test
    void whenInvalidName_thenReturns400(){

    }

    @Test
    void whenInvalidLastnames_thenReturns400(){

    }

    @Test
    void whenInvalidDateFormat_thenReturns400(){

    }

    // Testing checkRequestValidity
    @Test
    void whenValidParameters_thenNoExceptionsThrown() throws Exception{

    }

    @Test
    void whenEmptyName_thenThrowsException() throws Exception{

    }

    @Test
    void whenEmptyLastname_thenThrowsException() throws Exception{

    }

    @Test
    void whenInvalidDate_thenThrowsException() throws Exception{

    }

    // Testing checkBirthDateValidity
    @Test
    void whenBirthdateLaterThanCurrentDate_thenThrowsException() throws Exception{

    }

    @Test
    void whenBirthdateEarlierThanCurrentDate_thenNoExceptionsThrown() throws Exception{

    }

    //Testing handleException
    @Test
    void whenInvalidBirthDateException_thenReturnNotAcceptable(){

    }

    @Test
    void whenNullPointerException_thenReturnNotAcceptable(){

    }

    @Test
    void whenOtherException_thenReturnInternalServerError(){

    }



}
