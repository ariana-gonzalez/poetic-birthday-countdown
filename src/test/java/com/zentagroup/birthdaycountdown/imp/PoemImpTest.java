package com.zentagroup.birthdaycountdown.imp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PoemImpTest {
    @InjectMocks
    private PoemImp pi = new PoemImp();


    @BeforeAll
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test get a poem from poemist api")
    void testGetPoems() throws Exception {

    }

    @Test
    @DisplayName("Test get a poem from poemist api")
    void testGetRandomPoem() throws Exception {

    }



}
