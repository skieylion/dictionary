package com.dictionary.web.domain;

import com.dictionary.core.domain.Card;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.function.Predicate;

import lombok.Getter;
import lombok.Setter;

public abstract class Trainer extends VerticalLayout {
    @Setter
    private Predicate<String> inspector;
    @Setter
    @Getter
    private Card card;

    public abstract String getValue();

    public boolean inspect() {
        return inspector.test(getValue());
    }
}
