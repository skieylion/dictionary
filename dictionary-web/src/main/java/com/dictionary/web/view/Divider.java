package com.dictionary.web.view;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Divider extends Div {
    public Divider(String text) {
        setWidthFull();
        setHeight("20px");
        getStyle().set("position", "relative");
        getStyle().set("padding-top", "2px");

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setMargin(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setWidthFull();

        Html hr = new Html("<hr>");
        hr.getElement().getStyle().set("width", "100%");
        hr.getElement().getStyle().set("background-color", "#C6D8FF");
        layout.add(hr);

        Span span = new Span(text);
        span.getStyle().set("position", "relative");
        span.getStyle().set("top", "-38px");
        span.getStyle().set("background", "#fff");
        span.getStyle().set("color", "gray");
        layout.add(span);

        add(layout);
    }
}
