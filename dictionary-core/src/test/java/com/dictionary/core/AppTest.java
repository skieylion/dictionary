package com.dictionary.core;


import com.dictionary.core.domain.Card;

import static junit.framework.Assert.assertTrue;

public class AppTest {

    public void testApp() {
        Card card = new Card();
        card.setDefinition("Hello world");
        assertTrue(card.getDefinition().equals("Hello world"));
    }
}
