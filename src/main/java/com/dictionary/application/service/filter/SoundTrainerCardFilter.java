package com.dictionary.application.service.filter;

import com.dictionary.application.domain.Card;

import java.util.List;
import java.util.stream.Collectors;

public class SoundTrainerCardFilter extends CardFilterAbstract {
    public SoundTrainerCardFilter(CardFilter cardFilter) {
        super(cardFilter);
    }

    @Override
    public List<Card> apply() {
        return super.apply().stream()
                .filter(card -> FilterUtils.transcriptions(card.getTranscriptions()).size() > 0)
                .collect(Collectors.toList());
    }

}
