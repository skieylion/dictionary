package com.dictionary.web.view;

import com.dictionary.core.domain.Size;
import com.vaadin.flow.component.textfield.TextField;

public class CustomTextField {

    private final TextField textField = new TextField();

    public static CustomTextField builder() {
        return new CustomTextField();
    }

    public CustomTextField title(String text) {
        textField.setTitle(text);
        return this;
    }

    public CustomTextField value(String value) {
        textField.setValue(value);
        return this;
    }

    public CustomTextField placeholder(String placeholder) {
        textField.setPlaceholder(placeholder);
        return this;
    }

    public CustomTextField fullWidth() {
        textField.setWidth(Size.PERCENT_100);
        return this;
    }

    public CustomTextField clearButton(boolean isClearButton) {
        textField.setClearButtonVisible(isClearButton);
        return this;
    }

    public TextField build() {
        return textField;
    }
}
