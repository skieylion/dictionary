package com.dictionary.core;

import static junit.framework.Assert.assertTrue;

import com.dictionary.core.domain.Card;

public class AppTest {

  public void testApp() {
    Card card = new Card();
    card.setDefinition("Hello world");
    assertTrue(card.getDefinition().equals("Hello world"));
  }
}
