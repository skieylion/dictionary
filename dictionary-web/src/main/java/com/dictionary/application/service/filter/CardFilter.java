package com.dictionary.application.service.filter;

import com.dictionary.application.domain.Card;

import java.util.List;

public interface CardFilter {
    List<Card> apply();
}
