package com.dictionary.application.view.layout;

import com.dictionary.application.domain.Size;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CenterVerticalLayout extends VerticalLayout {
    public CenterVerticalLayout() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setWidth(Size.PERCENT_100);
    }
}
