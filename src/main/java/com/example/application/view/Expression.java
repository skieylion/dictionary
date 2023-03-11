package com.example.application.view;

import com.example.application.domain.Size;
import com.vaadin.flow.component.textfield.TextField;

import java.util.function.Consumer;

public class Expression extends FullHorizontalLayout {
    private final TextField expression;

    public Expression(final Consumer<String> change) {
        expression = new TextField();
        expression.setPlaceholder("expression...");
        expression.setWidth(Size.PERCENT_100);
        add(expression);
    }

    public String getText() {
        return expression.getValue();
    }

}

//Button button = new Button("find");
//button.addClickListener(e -> change.accept(expression.getValue()));
//add(button);