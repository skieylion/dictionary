package com.dictionary.application.domain;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public interface ComponentFactory {
    Button createButton();

    TextField createTextField();

    TextArea createTextArea();
}
