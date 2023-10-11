package com.dictionary.web.domain;

import com.dictionary.web.view.CardContainer;
import com.dictionary.web.view.MenuBarWrapper;

import java.util.Deque;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContextContainer {
    CardContainer container;
    MenuBarWrapper menu;
    Deque<CardContext> deque;
}
