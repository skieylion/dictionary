package com.example.application.domain;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class FragmentLayout {
    protected Button closeButton;
    protected Button plusButton;
    protected HorizontalLayout layout;
}