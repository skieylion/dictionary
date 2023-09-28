package com.dictionary.application.service.handler.context;

import com.dictionary.application.domain.CardContext;
import com.dictionary.application.domain.CardContextType;
import com.dictionary.application.domain.CardSoundContext;
import com.dictionary.application.view.box.SoundBox;
import com.dictionary.application.view.button.ButtonMini;
import com.vaadin.flow.component.icon.Icon;

import java.util.List;

public class CardContextSoundCreator extends CardContextBaseCreator {
    @Override
    CardContext createContext() {
        var sounds = new SoundBox();
        return CardSoundContext.builder()
                .soundBox(sounds)
                .title("sounds")
                .component(sounds)
                .contextType(CardContextType.SOUND)
                .buttons(List.of(ButtonMini.builder()
                        .icon(new Icon("lumo", "plus"))
                        .click(listener -> sounds.addSound())
                        .build()))
                .build();
    }
}
