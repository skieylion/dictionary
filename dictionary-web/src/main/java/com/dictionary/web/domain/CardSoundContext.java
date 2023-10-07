package com.dictionary.web.domain;

import com.dictionary.web.view.box.SoundBox;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CardSoundContext extends CardContext {
    private SoundBox soundBox;
}
