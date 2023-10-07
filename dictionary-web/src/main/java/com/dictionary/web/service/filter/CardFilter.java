package com.dictionary.web.service.filter;

import com.dictionary.web.domain.Card;

import java.util.List;

public interface CardFilter {
    List<Card> apply();
}
