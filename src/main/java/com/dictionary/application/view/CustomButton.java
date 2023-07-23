package com.dictionary.application.view;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class CustomButton {

    private final Button button;

    private CustomButton(Button button) {
        this.button = button;
    }

    public CustomButton name(String name) {
        if (!StringUtils.isBlank(name)) {
            button.setText(name);
        }
        return this;
    }

    public CustomButton icon(VaadinIcon icon) {
        if (Objects.nonNull(icon)) {
            button.setIcon(new Icon(icon));
        }
        return this;
    }

    public CustomButton width(String width) {
        if (!StringUtils.isBlank(width)) {
            button.setWidth(width);
        }
        return this;
    }

    public CustomButton enabled(boolean enabled) {
        button.setEnabled(enabled);
        return this;
    }

    public CustomButton theme(ButtonVariant variant) {
        button.addThemeVariants(variant);
        return this;
    }

    public <T extends Component> CustomButton clickListener(Runnable runnable) {
        button.addClickListener(listener -> runnable.run());
        return this;
    }

    public Button build() {
        //button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return button;
    }

    public static CustomButton builder() {
        return new CustomButton(new Button());
    }

    public static CustomButton builder(Button button) {
        return new CustomButton(button);
    }

}
