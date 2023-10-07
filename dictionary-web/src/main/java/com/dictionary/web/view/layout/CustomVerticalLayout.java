package com.dictionary.web.view.layout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CustomVerticalLayout {
    private final VerticalLayout verticalLayout = new VerticalLayout();

    public CustomVerticalLayout width(String width) {
        verticalLayout.setWidth(width);
        return this;
    }

    public CustomVerticalLayout add(Component... components) {
        verticalLayout.add(components);
        return this;
    }

    public static CustomVerticalLayout builder() {
        return new CustomVerticalLayout();
    }

    public VerticalLayout build() {
        return verticalLayout;
    }
}
