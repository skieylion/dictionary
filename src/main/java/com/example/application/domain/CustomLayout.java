package com.example.application.domain;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

public interface CustomLayout {
    CustomLayout align(FlexComponent.Alignment alignment);

    CustomLayout width(String width);

    CustomLayout height(String height);

    CustomLayout padding(boolean padding);

    CustomLayout margin(boolean margin);

    CustomLayout add(Component component);

}
