package com.dictionary.application.view.layout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class VerticalBorderLayout extends VerticalLayout {

    {
        getStyle().set("border-radius", "10px");
        getStyle().set("border", "3px solid #504F51");
    }

    public VerticalBorderLayout(Component... children) {
        super(children);
    }

    public VerticalBorderLayout() {
        super();
    }
}
