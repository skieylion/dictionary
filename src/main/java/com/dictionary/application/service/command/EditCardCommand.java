package com.dictionary.application.service.command;

import com.dictionary.application.domain.CardContext;
import com.dictionary.application.view.button.ButtonMini;
import com.vaadin.flow.component.icon.Icon;

import java.util.ArrayList;
import java.util.Optional;

public class EditCardCommand extends CardCommand {
    public EditCardCommand(CardContext context) {
        super(context);
    }

    @Override
    public void execute() {
        var left = context.getContainer().getLeftLayout();
        left.removeAll();
        left.add(context.getComponent());
        var right = context.getContainer().getUpLayout();
        var editButton = context.getButton();
        var buttonUndo = ButtonMini.builder().icon(new Icon("lumo", "undo"))
                .click(listener -> {
                    Optional.ofNullable(context.getDefaultComponent())
                            .ifPresent(context::setComponent);
                    Optional.ofNullable(context.getDefaultButtons())
                            .ifPresent(context::setButtons);
                    context.getContainer().getLeftLayout().removeAll();
                    context.getContainer().getLeftLayout().add(context.getComponent());
                    right.replace(listener.getSource(), editButton);
                })
                .build();
        right.replace(editButton, buttonUndo);
        var buttons = new ArrayList<>(context.getButtons());
        buttons.remove(editButton);
        context.setButtons(buttons);
        context.setButton(buttonUndo);
    }
}
