package com.dictionary.application.view.layout;

import com.dictionary.application.domain.Size;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class FullVerticalLayout extends VerticalLayout {
    public FullVerticalLayout() {
        setWidth(Size.PERCENT_100);
        setMargin(false);
        setPadding(false);
    }
}
