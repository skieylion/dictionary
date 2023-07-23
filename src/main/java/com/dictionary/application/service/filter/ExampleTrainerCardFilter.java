package com.dictionary.application.service.filter;

import com.dictionary.application.domain.Card;

import java.util.List;
import java.util.Objects;
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
