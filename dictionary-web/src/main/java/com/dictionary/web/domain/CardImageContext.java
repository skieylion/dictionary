package com.dictionary.web.domain;

import com.dictionary.web.view.Picture;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class CardImageContext extends CardContext {
    private Picture picture;
}
