package com.dictionary.application.domain;

import com.dictionary.application.view.Picture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class CardImageContext extends CardContext {
    private Picture picture;
}
