package com.example.application.domain;

import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ExampleLayout extends FragmentLayout {
    @Getter
    private TextField textField;
}
