package com.dictionary.application.domain;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Predicate;

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
