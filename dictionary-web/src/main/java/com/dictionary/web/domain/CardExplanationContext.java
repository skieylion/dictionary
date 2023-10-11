package com.dictionary.web.domain;

import com.vaadin.flow.component.textfield.TextArea;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class CardExplanationContext extends CardContext {
    private TextArea textArea;
}
