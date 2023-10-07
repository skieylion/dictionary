package com.dictionary.web.service.filter;

import com.dictionary.web.domain.Card;

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
