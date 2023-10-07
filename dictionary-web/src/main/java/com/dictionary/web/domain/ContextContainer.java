package com.dictionary.web.domain;

import com.dictionary.web.view.CardContainer;
import com.dictionary.web.view.MenuBarWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Deque;

@Getter
@AllArgsConstructor
public class ContextContainer {
    CardContainer container;
    MenuBarWrapper menu;
    Deque<CardContext> deque;
}
