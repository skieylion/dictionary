package com.dictionary.web.service.handler.context;

import com.dictionary.web.domain.CardContext;
import com.dictionary.web.domain.CardContextType;
import com.dictionary.web.domain.CardExplanationContext;
import com.dictionary.web.view.CustomTextArea;

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
