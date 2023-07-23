package com.dictionary.application.service.filter;

import com.dictionary.application.domain.Card;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AssociationTrainerCardFilter extends CardFilterAbstract {
    public AssociationTrainerCardFilter(CardFilter cardFilter) {
        super(cardFilter);
    }

    @Override
    public List<Card> apply() {
        return super.apply().stream()
                .filter(card -> Objects.nonNull(card.getDefinition())
                        || Objects.nonNull(card.getPictureFile()))
                .collect(Collectors.toList());
    }

}
