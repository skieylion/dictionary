package com.dictionary.application.domain;

import com.dictionary.application.view.CardContainer;
import com.dictionary.application.view.MenuBarWrapper;
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
