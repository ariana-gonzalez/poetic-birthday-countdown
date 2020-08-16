package com.zentagroup.birthdaycountdown.controller;

import com.zentagroup.birthdaycountdown.exception.InvalidDateException;
import com.zentagroup.birthdaycountdown.util.DateUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CountdownControllerTest {
    @InjectMocks
    private CountdownController cc = new CountdownController();


    @BeforeAll
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCheckRequestValidity() throws Exception {
        // When null data then throw exception
        assertThrows(NullPointerException.class, () -> cc.checkRequestValidity(null, "Gonzalez", LocalDate.of(2020, 01, 12)));
        // When birthdate after current date then throw exception
        assertThrows(InvalidDateException.class, () -> cc.checkRequestValidity("Ariana", "Gonzalez", LocalDate.of(2021, 01, 12)));
        // When empty spaces data then throw exception
        assertThrows(NullPointerException.class, () -> cc.checkRequestValidity(" ", "Gonzalez", LocalDate.of(2020, 01, 12)));
    }

    @Test
    void testCheckBirthDateValidity() throws Exception {
        // When birthdate after current date then throw exception
        assertThrows(InvalidDateException.class, () -> cc.checkBirthDateValidity(LocalDate.of(2023, 01, 12)));
    }



}
