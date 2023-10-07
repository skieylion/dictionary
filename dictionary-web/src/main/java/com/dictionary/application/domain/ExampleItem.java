package com.dictionary.application.domain;

import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ExampleItem extends ListBoxItem {
    @Getter
    private TextField textField;
}
