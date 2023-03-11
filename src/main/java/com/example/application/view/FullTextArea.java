package com.example.application.view;

import com.example.application.domain.Size;
import com.vaadin.flow.component.textfield.TextArea;

public class FullTextArea extends TextArea {
    private FullTextArea() {
        setWidth(Size.PERCENT_100);
    }

    public static FullTextArea createInstance() {
        return new FullTextArea();
    }

    public FullTextArea placeholder(String placeholder) {
        setPlaceholder(placeholder);
        return this;
    }
}
