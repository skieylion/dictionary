package com.dictionary.application.view;

import com.dictionary.application.domain.Size;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.internal.Pair;

import java.util.ArrayList;
import java.util.List;

public class SlotSelect extends FullVerticalLayout {

    private static final List<Pair<Long, String>> slots;

    static {
        slots = new ArrayList<>();
        slots.add(new Pair<>(1L, "The weather"));
        slots.add(new Pair<>(2L, "Vegetables"));
        slots.add(new Pair<>(3L, "Fruits"));
    }

    public SlotSelect() {
        MultiSelectComboBox<Pair<Long, String>> comboBox = new MultiSelectComboBox<>();
        comboBox.setItems(slots);
        comboBox.setItemLabelGenerator(Pair::getSecond);
        comboBox.setWidth(Size.PERCENT_100);
        add(comboBox);
    }
}
