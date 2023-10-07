package com.dictionary.application.view;

import com.dictionary.application.domain.ElementType;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class CheckboxGroupView extends VerticalLayout {
    private final CheckboxGroup<String> checkboxGroup;
    private BiConsumer<ElementType, Boolean> listener;

    public CheckboxGroupView(Set<ElementType> items) {
        setWidthFull();
        setPadding(false);
        setMargin(false);
        setAlignItems(FlexComponent.Alignment.CENTER);
        checkboxGroup = new CheckboxGroup<>();
        setItems(items);
        checkboxGroup.addValueChangeListener(l -> {
            var set = except(l.getValue(), l.getOldValue());
            boolean isAdded = set.size() > 0;
            set.addAll(except(l.getOldValue(), l.getValue()));
            set.stream()
                    .map(item -> ElementType.valueOf(item.toUpperCase()))
                    .forEach(elementType -> listener.accept(elementType, isAdded));
        });
        add(checkboxGroup);
    }

    public final void setItems(Set<ElementType> items) {
        Set<String> checkboxes = new HashSet<>();
        if (items != null) {
            items.forEach(item -> checkboxes.add(item.name().toLowerCase()));
        }
        checkboxGroup.setItems(checkboxes);
    }

    public Set<ElementType> getSelected() {
        return checkboxGroup.getSelectedItems().stream()
                .map(ElementType::valueOf)
                .collect(Collectors.toSet());
    }

    public void setChangeEventListener(BiConsumer<ElementType, Boolean> listener) {
        this.listener = listener;
    }

    public void select(Set<ElementType> items) {
        checkboxGroup.clear();
        checkboxGroup.select(items.stream()
                .map(Enum::name)
                .collect(Collectors.toSet()));
    }

    private static Set<String> except(Set<String> A, Set<String> B) {
        Set<String> result = new HashSet<>(A);
        result.removeAll(B);
        return result;
    }
}
