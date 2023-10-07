package com.dictionary.web.domain;

import com.vaadin.flow.component.textfield.TextArea;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CardExplanationContext extends CardContext {
    private TextArea textArea;
}
