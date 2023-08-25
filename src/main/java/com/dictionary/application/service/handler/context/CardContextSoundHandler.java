package com.dictionary.application.service.handler.context;

import com.dictionary.application.domain.CardContext;
import com.dictionary.application.view.box.ExampleBox;
import com.dictionary.application.view.box.SoundBox;
import com.dictionary.application.view.button.ButtonMini;
import com.vaadin.flow.component.icon.Icon;

import java.util.List;

public class CardContextSoundHandler extends CardContextBaseHandler {
    @Override
    CardContext context() {
        var sounds = new SoundBox();
        return CardContext.builder()
                .title("sounds")
                .component(sounds)
                .buttons(List.of(ButtonMini.builder()
                        .icon(new Icon("lumo", "plus"))
                        .click(listener -> sounds.addSound())
                        .build()))
                .build();
    }
}
