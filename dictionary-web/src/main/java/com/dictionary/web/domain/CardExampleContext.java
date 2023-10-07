package com.dictionary.web.domain;

import com.dictionary.web.view.box.ExampleBox;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class CardExampleContext extends CardContext {
    private ExampleBox exampleBox;
}
