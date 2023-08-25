package com.dictionary.application.service.handler.context;

import com.dictionary.application.domain.CardContext;
import com.dictionary.application.view.box.ExampleBox;
import com.dictionary.application.view.button.ButtonMini;
import com.vaadin.flow.component.icon.Icon;

import java.util.List;

public class CardContextExampleHandler extends CardContextBaseHandler {
    @Override
    CardContext context() {
        var examples = new ExampleBox();
        return CardContext.builder()
                .title("examples")
                .buttons(List.of(ButtonMini.builder()
                        .icon(new Icon("lumo", "plus"))
                        .click(listener -> examples.addExample())
                        .build()))
                .component(examples)
                .build();
    }
}
