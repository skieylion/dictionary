package com.dictionary.application.service.filter;

import com.dictionary.application.domain.Card;

import java.util.List;

public class CardSource implements CardFilter {

    private final List<Card> cards;

    public CardSource(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> apply() {
        return cards;
    }
}
