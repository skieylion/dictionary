package com.dictionary.web.service.filter;

import com.dictionary.web.domain.Card;

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
