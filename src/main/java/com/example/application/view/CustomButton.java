package com.example.application.view;


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

    public Button build() {
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return button;
    }

    public static CustomButton builder() {
        return new CustomButton(new Button());
    }

}
