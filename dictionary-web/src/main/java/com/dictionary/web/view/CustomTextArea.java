package com.dictionary.web.view;

import com.dictionary.core.domain.Size;
import com.vaadin.flow.component.textfield.TextArea;

public class CustomTextArea {

    private final TextArea textArea = new TextArea();

    public static CustomTextArea builder() {
        return new CustomTextArea();
    }

    public CustomTextArea placeholder(String placeholder) {
        textArea.setPlaceholder(placeholder);
        return this;
    }

    public CustomTextArea fullWidth() {
        textArea.setWidth(Size.PERCENT_100);
        return this;
    }

    public CustomTextArea clearButton(boolean isClearButton) {
        textArea.setClearButtonVisible(isClearButton);
        return this;
    }

    public TextArea build() {
        return textArea;
    }
}
