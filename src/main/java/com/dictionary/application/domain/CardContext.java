package com.dictionary.application.domain;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class CardContext {
    private Component component;
    private List<Button> buttons;
}
