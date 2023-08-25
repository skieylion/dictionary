package com.dictionary.application.view.button;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;

public class ButtonMini extends Button {
    private static final String SIZE = "20px";

    {
        getStyle().set("min-width", SIZE);
        getStyle().set("width", SIZE);
        getStyle().set("min-height", SIZE);
        getStyle().set("height", SIZE);
        getStyle().set("padding", "0");
    }

    public ButtonMini(Icon icon) {
        super(icon);
        icon.getStyle().set("min-width", SIZE);
        icon.getStyle().set("width", SIZE);
        icon.getStyle().set("min-height", SIZE);
        icon.getStyle().set("height", SIZE);
    }

    public static ButtonMiniBuilder builder() {
        return new ButtonMiniBuilder();
    }

    public static class ButtonMiniBuilder {
        private Icon icon;
        private ComponentEventListener<ClickEvent<Button>> listener;

        public ButtonMiniBuilder icon(Icon icon) {
            this.icon = icon;
            return this;
        }

        public ButtonMiniBuilder click(ComponentEventListener<ClickEvent<Button>> listener) {
            this.listener = listener;
            return this;
        }

        public Button build() {
            Button button = new ButtonMini(icon);
            button.addClickListener(listener);
            return button;
        }

    }
}
