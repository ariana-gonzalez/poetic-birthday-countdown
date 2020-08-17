package com.zentagroup.birthdaycountdown.imp;

import com.zentagroup.birthdaycountdown.dto.Poem;
import com.zentagroup.birthdaycountdown.exception.CouldNotGetPoemsException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PoemImpTest {
    private PoemImp pi = new PoemImp();

    @Test
    void testGetPoems() throws CouldNotGetPoemsException {
        // When called then a list is returned
        assertTrue(pi.getPoems() instanceof List);
        // When called returned list contains elements
        assertFalse(pi.getPoems().isEmpty());
        // When called returned list contains elements of type Poem
        assertTrue(pi.getPoems().get(0) instanceof Poem);
    }

    @Test
    void testGetRandomPoem() throws CouldNotGetPoemsException{
        // When called returns Poem
        assertTrue(pi.getRandomPoem(pi.getPoems()) instanceof Poem);
        // When called two times with same parameters returns different Poems
        assertTrue(pi.getRandomPoem(pi.getPoems()) != pi.getRandomPoem(pi.getPoems()));
    }
}
