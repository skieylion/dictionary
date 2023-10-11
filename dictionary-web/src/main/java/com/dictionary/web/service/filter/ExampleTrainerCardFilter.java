package com.dictionary.web.service.filter;

import com.dictionary.core.domain.Card;

import java.util.List;
import java.util.stream.Collectors;

public class ExampleTrainerCardFilter extends CardFilterAbstract {
    public ExampleTrainerCardFilter(CardFilter cardFilter) {
        super(cardFilter);
    }

    @Override
    public List<Card> apply() {
        return super.apply().stream()
                .filter(card -> FilterUtils.examples(card.getExamples()).size() > 0)
                .collect(Collectors.toList());
    }
}
