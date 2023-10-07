package com.dictionary.web.service.handler.context;

import com.dictionary.web.domain.CardContext;
import com.dictionary.web.domain.CardContextType;
import com.dictionary.web.domain.CardExampleContext;
import com.dictionary.web.view.box.ExampleBox;
import com.dictionary.web.view.button.ButtonMini;
import com.vaadin.flow.component.icon.Icon;

import java.util.List;

public class CardContextExampleCreator extends CardContextBaseCreator {
    @Override
    CardContext createContext() {
        var examples = new ExampleBox();
        return CardExampleContext.builder()
                .exampleBox(examples)
                .title("examples")
                .buttons(List.of(ButtonMini.builder()
                        .icon(new Icon("lumo", "plus"))
                        .click(listener -> examples.addExample())
                        .build()))
                .component(examples)
                .contextType(CardContextType.EXAMPLE)
                .build();
    }
}
