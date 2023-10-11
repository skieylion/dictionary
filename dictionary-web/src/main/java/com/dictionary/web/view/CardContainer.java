package com.dictionary.web.view;

import com.dictionary.web.domain.CardContext;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Collection;
import java.util.Set;

public class CardContainer extends VerticalLayout {
    private static final String DARK_COLOR = "#212A36";
    private static final String WIDTH_CONTAINER = "100%";
    private static final String WIDTH_CONTEXT_MENU = "0%";
    private static final String BORDER_CONTAINER_STYLE = "3px solid " + DARK_COLOR;
    private static final String BORDER_RADIUS = "10px";

    {
        setSpacing(false);
        setWidthFull();
        setPadding(false);
        setMargin(false);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setHorizontalComponentAlignment(Alignment.CENTER);
    }

    private enum Align {
        TOP,
        BOTTOM
    }

    private final VerticalLayout header;
    private final VerticalLayout footer;

    public CardContainer() {
        header = createPanel(Align.TOP);
        footer = createPanel(Align.BOTTOM);
        add(header);
        add(footer);
    }

    public void setHeader(Component component) {
        header.removeAll();
        var hl = new HorizontalLayout(component);
        hl.setWidthFull();
        header.add(hl);
    }

    public void setFooter(Component component) {
        footer.removeAll();
        var hl = new HorizontalLayout(component);
        hl.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        hl.setVerticalComponentAlignment(Alignment.CENTER);
        footer.add(hl);
    }

    public CardContext.Container add(Component component, Collection<Button> icons) {
        var left = createContainerLayout();
        var layout = new VerticalLayout(component);
        layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        layout.setAlignItems(Alignment.CENTER);
        left.add(layout);
        var up = createContextMenuLayout();
        icons.forEach(button -> button.getElement().getStyle().set("margin", "2px"));
        icons.forEach(up::add);
        var last = getComponentAt(getComponentCount() - 1);
        remove(last);
        var layoutComponent = createLine(left, up);
        add(layoutComponent);
        add(last);
        return new CardContext.Container(layout, up, layoutComponent);
    }

    public void drop(Component component) {
        remove(component);
    }

    private static VerticalLayout createPanel(Align align) {
        String alignment = align.name().toLowerCase();
        VerticalLayout header = new VerticalLayout();
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.getStyle().set("background-color", DARK_COLOR);
        header.getStyle().set("border-" + alignment + "-left-radius", BORDER_RADIUS);
        header.getStyle().set("border-" + alignment + "-right-radius", BORDER_RADIUS);
        header.getStyle().set("border", BORDER_CONTAINER_STYLE);
        header.setWidthFull();
        return header;
    }

    private static VerticalLayout createContainerLayout() {
        var layout = new VerticalLayout();
        layout.setWidth(WIDTH_CONTAINER);
        layout.setSpacing(false);
        layout.setPadding(false);
        layout.setMargin(false);
        Set.of("border-left", "border-right")
                .forEach(name -> layout.getStyle().set(name, BORDER_CONTAINER_STYLE));
        return layout;
    }

    private static HorizontalLayout createContextMenuLayout() {
        var layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.getStyle().set("border-top", "2px solid #504F51");
        Set.of("border-left", "border-right")
                .forEach(name -> layout.getStyle().set(name, BORDER_CONTAINER_STYLE));
        layout.setJustifyContentMode(JustifyContentMode.END);
        layout.setSpacing(false);
        layout.setPadding(false);
        layout.setMargin(false);
        // layout.getStyle().set("position", "relative");
        // layout.getStyle().set("left", "-25px");
        layout.getStyle().set("padding", "1px");
        layout.getStyle().set("background-color", DARK_COLOR);
        return layout;
    }

    private static VerticalLayout createLine(VerticalLayout left, HorizontalLayout up) {
        var layout = new VerticalLayout();
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setPadding(false);
        layout.setMargin(false);
        layout.add(up, left);
        return layout;
    }
}
