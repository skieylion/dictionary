package com.dictionary.application.service.handler.context;

import com.dictionary.application.domain.CardContext;
import com.dictionary.application.view.CustomTextArea;
import com.dictionary.application.view.box.ExampleBox;
import com.dictionary.application.view.button.ButtonMini;
import com.vaadin.flow.component.icon.Icon;

import java.util.ArrayList;
import java.util.List;

public class CardContextExplanationHandler extends CardContextBaseHandler {
    @Override
    CardContext context() {
        return CardContext.builder()
                .title("explanation")
                .buttons(new ArrayList<>())
                .component(CustomTextArea.builder().placeholder("explanation...").fullWidth().build())
                .build();
    }
}
