package com.zentagroup.birthdaycountdown.imp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountdownImpTest {
    private CountdownImp ci = new CountdownImp();

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
