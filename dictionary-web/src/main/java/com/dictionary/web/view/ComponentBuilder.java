package com.dictionary.web.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;

import java.lang.reflect.InvocationTargetException;

public final class ComponentBuilder<T extends Component & HasComponents & HasSize> {

    private final T component;

    private ComponentBuilder(Class<?> clazz) {
        try {
            this.component = (T) clazz.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Couldn't call a constructor", e);
        }
    }

    private ComponentBuilder(T component) {
        this.component = component;
    }

    public static ComponentBuilder<?> builder(Class<? extends Component> clazz) {
        return new ComponentBuilder<>(clazz);
    }

    public static <T extends Component & HasComponents & HasSize> ComponentBuilder<?> builder(T component) {
        return new ComponentBuilder<>(component);
    }

    public ComponentBuilder<?> emptyIndent(boolean margin, boolean padding) {
        ThemableLayout themableLayout = (ThemableLayout) component;
        themableLayout.setMargin(margin);
        themableLayout.setPadding(padding);
        return this;
    }

    public ComponentBuilder<?> width(String width) {
        component.setWidth(width);
        return this;
    }

    public ComponentBuilder<?> height(String height) {
        component.setHeight(height);
        return this;
    }

    public ComponentBuilder<?> size(String width, String height) {
        width(width);
        height(height);
        return this;
    }

    public ComponentBuilder<?> align(FlexComponent.Alignment alignment) {
        FlexComponent flexComponent = (FlexComponent) component;
        flexComponent.setAlignItems(alignment);
        return this;
    }

    public <K extends Component> ComponentBuilder<?> add(K component) {
        this.component.add(component);
        return this;
    }

    public <T> T build() {
        return (T) component;
    }


}
