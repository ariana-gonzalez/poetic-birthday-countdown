package com.zentagroup.birthdaycountdown.imp;

import com.zentagroup.birthdaycountdown.dto.Poem;
import com.zentagroup.birthdaycountdown.util.DateUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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
