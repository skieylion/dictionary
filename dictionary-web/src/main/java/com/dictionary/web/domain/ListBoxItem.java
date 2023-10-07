package com.dictionary.web.domain;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ListBoxItem {
    protected Button closeButton;
    protected Button plusButton;
    protected HorizontalLayout layout;
}