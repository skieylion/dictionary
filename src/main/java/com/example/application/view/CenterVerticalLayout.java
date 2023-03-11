package com.example.application.view;

import com.example.application.domain.Size;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CenterVerticalLayout extends VerticalLayout {
    public CenterVerticalLayout() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setWidth(Size.PERCENT_100);
    }
}
