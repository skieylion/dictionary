package com.dictionary.application.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CellTextField extends HorizontalLayout {

    public CellTextField(int count, String size) {
        setAlignItems(Alignment.CENTER);
        while (count-- > 0) {
            var textField = new TextField();
            textField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
            textField.setWidth(size);
            textField.setHeight(size);
            textField.setMaxLength(1);
            add(textField);
        }
    }
}
