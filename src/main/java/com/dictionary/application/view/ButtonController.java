package com.dictionary.application.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ButtonController extends VerticalLayout {
    {
        setWidthFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        getStyle().set("border-radius", "10px");
        getStyle().set("padding", "10px");
        getStyle().set("border", "3px solid #504F51");
    }


    private ButtonController() {
    }

    public static ButtonControllerBuilder builder() {
        return new ButtonControllerBuilder();
    }

    public static class ButtonControllerBuilder {
        private final List<Button> buttons = new ArrayList<>();

        public ButtonControllerBuilder button(Button button, Consumer<Button> consumer) {
            buttons.add(button);
            button.addClickListener(listener -> {
                consumer.accept(button);
            });
            return this;
        }

        public ButtonController build() {
            var ctrl = new ButtonController();
            var layout = new HorizontalLayout();
            buttons.forEach(layout::add);
            ctrl.add(layout);
            return ctrl;
        }

    }


}
