package com.dictionary.application.service.handler.context;

import com.dictionary.application.domain.CardContext;
import com.dictionary.application.domain.CardContextType;
import com.dictionary.application.domain.CardExplanationContext;
import com.dictionary.application.view.CustomTextArea;

import java.util.ArrayList;

public class CardContextExplanationCreator extends CardContextBaseCreator {
    @Override
    CardContext createContext() {
        var textArea = CustomTextArea.builder().placeholder("explanation...").fullWidth().build();
        return CardExplanationContext.builder()
                .textArea(textArea)
                .title("explanation")
                .buttons(new ArrayList<>())
                .component(textArea)
                .contextType(CardContextType.EXPLANATION)
                .build();
    }
}
