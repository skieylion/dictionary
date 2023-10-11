package com.dictionary.web.view;

import com.dictionary.core.domain.Size;
import com.dictionary.web.domain.ElementType;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.internal.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class ElementList {
    private final MultiSelectComboBox<Pair<Long, String>> elements = new MultiSelectComboBox<>();
    private final List<BiConsumer<ElementType, Boolean>> listeners = new ArrayList<>();

    public ElementList() {
        var items = ElementType.getList();
        elements.setItems(items);
        elements.select(items);
        elements.setItemLabelGenerator(Pair::getSecond);
        elements.addValueChangeListener(
                l ->
                        setValueChangeListener(
                                convertPairToStr(l.getValue()), convertPairToStr(l.getOldValue())));
        elements.setWidth(Size.PERCENT_100);
    }

    public MultiSelectComboBox<Pair<Long, String>> getMultiSelectComboBox() {
        return elements;
    }

    public void select(Set<ElementType> items) {
        elements.clear();
        elements.select(ElementType.getList(items));
    }

    public Set<ElementType> getSelected() {
        return elements.getSelectedItems().stream()
                .map(p -> ElementType.valueOf(p.getSecond().toUpperCase()))
                .collect(Collectors.toSet());
    }

    public void addChangeEventListener(BiConsumer<ElementType, Boolean> listener) {
        listeners.add(listener);
    }

    private static Set<String> convertPairToStr(Set<Pair<Long, String>> value) {
        return value.stream().map(Pair::getSecond).collect(Collectors.toSet());
    }

    private void setValueChangeListener(Set<String> value, Set<String> oldValue) {
        boolean isAdded = except(value, oldValue).size() > 0;
        getSelected(value, oldValue).stream()
                .map(item -> ElementType.valueOf(item.toUpperCase()))
                .forEach(
                        elementType -> listeners.forEach(listener -> listener.accept(elementType, isAdded)));
    }

    private static Set<String> getSelected(Set<String> value, Set<String> oldValue) {
        Set<String> items = new HashSet<>(except(value, oldValue));
        items.addAll(except(oldValue, value));
        return items;
    }

    private static Set<String> except(Set<String> set1, Set<String> set2) {
        Set<String> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }
}
