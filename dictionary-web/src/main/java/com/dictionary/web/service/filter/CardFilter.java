package com.dictionary.web.service.filter;

import com.dictionary.core.domain.Card;

import java.util.List;

public interface CardFilter {
    List<Card> apply();
}
