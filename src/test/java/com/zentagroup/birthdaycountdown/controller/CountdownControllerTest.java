package com.zentagroup.birthdaycountdown.controller;

import com.zentagroup.birthdaycountdown.dto.RespCountdownDto;
import com.zentagroup.birthdaycountdown.exception.InvalidDateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CountdownControllerTest {
    private CountdownController cc = new CountdownController();

    @Test
    void testGetBirthdayCountdown(){
        // When called with valid arguments returns ResponseBody with RespCountdownDto
        assertTrue(cc.getBirthdayCountdown("Ariana", "Gonzalez", LocalDate.of(2019, 03, 12)).getBody() instanceof RespCountdownDto);
        // When called with invalid arguments returns ResponseEntity with status 406 NOT_ACCEPTABLE
        assertEquals("406 NOT_ACCEPTABLE", cc.getBirthdayCountdown(" ", "Gonzalez", LocalDate.of(2020, 03, 12)).getStatusCode().toString());
        // When called with invalid date returns ResponseEntity with status 406 NOT_ACCEPTABLE
        assertEquals("406 NOT_ACCEPTABLE", cc.getBirthdayCountdown("Ariana", "Gonzalez", LocalDate.of(2021, 04, 23)).getStatusCode().toString());
    }

    @Test
    void testCheckRequestValidity() throws Exception {
        // When all data valid then doesn't throw any exception
        assertDoesNotThrow(()-> cc.checkRequestValidity("Ariana", "Gonzalez", LocalDate.of(1999, 3, 20)));
        // When null data then throw exception
        assertThrows(NullPointerException.class, () -> cc.checkRequestValidity(null, "Gonzalez", LocalDate.of(2020, 01, 12)));
        // When birthdate after current date then throw exception
        assertThrows(InvalidDateException.class, () -> cc.checkRequestValidity("Ariana", "Gonzalez", LocalDate.of(2021, 01, 12)));
        // When empty spaces data then throw exception
        assertThrows(NullPointerException.class, () -> cc.checkRequestValidity(" ", "Gonzalez", LocalDate.of(2020, 01, 12)));
    }

    @Test
    void testCheckBirthDateValidity() throws Exception {
        // When birth date after current date then throw exception
        assertThrows(InvalidDateException.class, () -> cc.checkBirthDateValidity(LocalDate.of(2023, 01, 12)));
        // When birth date before current date doesn't throw any exception
        assertDoesNotThrow(() -> cc.checkBirthDateValidity(LocalDate.of(2016, 01, 12)));
    }
}
