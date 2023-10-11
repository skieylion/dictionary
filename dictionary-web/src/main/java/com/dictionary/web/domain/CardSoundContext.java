package com.dictionary.web.domain;

import com.dictionary.web.view.box.SoundBox;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class CardSoundContext extends CardContext {
    private SoundBox soundBox;
}
