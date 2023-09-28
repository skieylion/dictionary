package com.dictionary.application.domain;

import com.vaadin.flow.component.textfield.TextArea;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CardExplanationContext extends CardContext {
    private TextArea textArea;
}
