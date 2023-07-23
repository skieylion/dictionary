package com.dictionary.application.service.filter;

import com.dictionary.application.domain.Card;

import java.util.List;

public class CardFilterAbstract implements CardFilter {
    private final CardFilter cardFilter;

    public CardFilterAbstract(CardFilter cardFilter) {
        this.cardFilter = cardFilter;
    }

    @Override
    public List<Card> apply() {
        return cardFilter.apply();
    }
}
