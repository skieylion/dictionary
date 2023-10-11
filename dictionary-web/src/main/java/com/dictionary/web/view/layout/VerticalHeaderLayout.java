package com.dictionary.web.view.layout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;

public class VerticalHeaderLayout extends VerticalLayout {

    {
        setSpacing(false);
        setPadding(false);
        setMargin(false);
        getStyle().set("border-radius", "10px");
        getStyle().set("border", "3px solid #212A36");
    }

    @Getter
    private final VerticalLayout layout;
    @Getter
    private final HorizontalLayout header;

    public VerticalHeaderLayout(String caption) {
        layout = new VerticalLayout();
        header = new HorizontalLayout();
        header(caption);
        super.add(layout);
    }

    public VerticalHeaderLayout(String caption, Component component) {
        this(caption);
        add(component);
    }

    private void header(String caption) {
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.getStyle().set("background-color", "#212A36");
        Span span = new Span(caption);
        span.getStyle().set("font-weight", "bold");
        span.setWidthFull();
        span.getStyle().set("text-align", "center");
        header.add(span);
        header.setWidthFull();
        header.setSpacing(false);
        super.add(header);
    }

    @Override
    public void add(Component... components) {
        layout.add(components);
    }
}
