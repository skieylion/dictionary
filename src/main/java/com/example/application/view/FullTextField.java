package com.example.application.view;

import com.example.application.domain.Size;
import com.vaadin.flow.component.textfield.TextField;

public class FullTextField extends TextField {
    private FullTextField() {
        setClearButtonVisible(true);
        setWidth(Size.PERCENT_100);
    }

    public static FullTextField createInstance() {
        return new FullTextField();
    }

    public FullTextField placeholder(String placeholder) {
        setPlaceholder(placeholder);
        return this;
    }
}
