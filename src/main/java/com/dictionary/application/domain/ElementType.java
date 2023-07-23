package com.dictionary.application.domain;

import com.vaadin.flow.internal.Pair;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum ElementType {
    AUDIO("audio"), IMAGE("image"), EXAMPLES("examples"), EXPLANATION("explanation"), EXPRESSION("expression");

    private final String text;

    ElementType(String text) {
        this.text = text;
    }

    public static Set<Pair<Long, String>> getList() {
        return Arrays.stream(ElementType.values())
                .filter(elementType -> !elementType.equals(EXPRESSION))
                .map(elementType -> new Pair<>((long) elementType.ordinal(), elementType.text))
                .collect(Collectors.toSet());
    }

    public static Set<Pair<Long, String>> getList(Set<ElementType> items) {
        return items.stream()
                .map(elementType -> new Pair<>((long) elementType.ordinal(), elementType.text))
                .collect(Collectors.toSet());
    }
}
