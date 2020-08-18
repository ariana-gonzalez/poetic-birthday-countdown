package com.zentagroup.birthdaycountdown.imp;

import com.zentagroup.birthdaycountdown.dto.Poem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountdownImpTest {
    private CountdownImp ci = new CountdownImp();

    @Test
    @DisplayName("Test get birthday countdown wrapper class")
    void testGetBirthdayCountdown() throws Exception {
        // When given date different to current date return countdown with number of days until birthday
        boolean isNumber = (ci.getBirthdayCountdown("Ariana", "Gonzalez", LocalDate.of(1999, 3, 12)).getCountdownResult() instanceof Integer);
        assertTrue(isNumber);
    }

    @Test
    @DisplayName("Test get birthday countdown based on a custom date")
    void testGetBirthdayCountdownForDates() throws Exception {
        // When is birthday return countdown with poem
        boolean isPoem = (ci.getBirthdayCountdownForDates("Ariana", "Gonzalez", LocalDate.of(1999, 3, 12), LocalDate.of(2020, 3, 12)).getCountdownResult() instanceof Poem);
        assertTrue(isPoem);
        // When isn't birthday return countdown with number of days until birthday
        boolean isNumber = (ci.getBirthdayCountdownForDates("Ariana", "Gonzalez", LocalDate.of(1999, 3, 12), LocalDate.of(1999, 5, 9)).getCountdownResult() instanceof Integer);
        assertTrue(isNumber);
    }

    @Test
    @DisplayName("Test get first word")
    void testGetFirstWord() {
        // When two words then return first word
        assertEquals("Two", ci.getFirstWord("Two words"));
        // When one word return that one word
        assertEquals("One", ci.getFirstWord("One"));
        // When more than two words return the first word
        assertEquals("Lots", ci.getFirstWord("Lots of words"));
        // When no words then return empty string
        assertEquals("", ci.getFirstWord(""));
    }
}
