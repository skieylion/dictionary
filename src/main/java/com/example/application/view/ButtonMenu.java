package com.example.application.view;

import com.example.application.domain.Size;
import com.vaadin.flow.component.button.Button;

public class ButtonMenu extends FullHorizontalLayout {

    private ButtonMenu() {
    }

    public ButtonMenu button(String caption, Runnable runnable) {
        Button button = new Button(caption);
        button.addClickListener(event -> runnable.run());
        button.setWidth(Size.PERCENT_100);
        add(button);
        return this;
    }

    public static ButtonMenu createInstance() {
        return new ButtonMenu();
    }
}
