package com.dictionary.application.service;

import com.dictionary.application.domain.CardContext;
import com.dictionary.application.view.ButtonMini;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;

public class EditCardCommand extends CardCommand {
    public EditCardCommand(CardContext context) {
        super(context);
    }

    @Override
    public void execute() {
        var left = context.getContainer().getLeftLayout();
        left.removeAll();
        left.add(context.getComponent());
        var right = context.getContainer().getRightLayout();
        var button = ButtonMini.builder().icon(new Icon("lumo", "checkmark")).build();
        right.replace(context.getButton(), button);
        var buttons = new ArrayList<>(context.getButtons());
        buttons.remove(context.getButton());
        context.setButtons(buttons);
        context.setButton(button);
    }
}
