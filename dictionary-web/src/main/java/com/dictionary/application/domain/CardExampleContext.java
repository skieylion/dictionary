package com.dictionary.application.domain;

import com.dictionary.application.view.box.ExampleBox;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public class CardExampleContext extends CardContext {
    private ExampleBox exampleBox;
}
