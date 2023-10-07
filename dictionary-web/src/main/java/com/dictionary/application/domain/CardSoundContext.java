package com.dictionary.application.domain;

import com.dictionary.application.view.box.SoundBox;
import com.vaadin.flow.component.textfield.TextField;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class CardSoundContext extends CardContext {
    private SoundBox soundBox;
}
