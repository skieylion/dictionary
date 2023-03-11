package com.example.application.view;

import com.example.application.domain.Size;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class FullHorizontalLayout extends HorizontalLayout {
    public FullHorizontalLayout() {
        setWidth(Size.PERCENT_100);
        setMargin(false);
        setPadding(false);
    }

    public static HorizontalLayout createInstance() {
        return new FullHorizontalLayout();
    }
}
