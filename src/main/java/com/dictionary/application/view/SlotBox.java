package com.dictionary.application.view;

import com.dictionary.application.domain.Size;
import com.dictionary.application.domain.Slot;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.internal.Pair;

import java.util.Collection;
import java.util.stream.Collectors;

public class SlotBox extends VerticalLayout {
    private final MultiSelectComboBox<Pair<Long, String>> slotBox;
    private final Collection<Slot> slots;

    public SlotBox(Collection<Slot> slots) {
        this.slots = slots;
        slotBox = new MultiSelectComboBox<>();
        slotBox.setItemLabelGenerator(Pair::getSecond);
        slotBox.setWidth(Size.PERCENT_100);
        slotBox.setItems(slots.stream()
                .map(slot -> new Pair<>(slot.getId(), slot.getName()))
                .collect(Collectors.toList()));
        add(slotBox);
    }

    public Collection<Slot> getSelectedSlots() {
        Collection<Long> ids = slotBox.getSelectedItems().stream()
                .map(Pair::getFirst)
                .collect(Collectors.toList());
        return slots.stream()
                .filter(slot -> ids.contains(slot.getId()))
                .collect(Collectors.toList());
    }

    public void selectSlots(Collection<Slot> slots) {
        slotBox.select(slots.stream()
                .map(slot -> new Pair<>(slot.getId(), slot.getName()))
                .collect(Collectors.toList()));
    }
}
