package com.dictionary.application.service;

import com.dictionary.application.domain.CardContext;
import com.dictionary.application.view.ButtonMini;
import com.vaadin.flow.component.icon.Icon;

import java.util.ArrayList;

public class NewCardCommand extends CardCommand {

    public NewCardCommand(CardContext context) {
        super(context);
    }

    @Override
    public void execute() {
        var removeButton = ButtonMini.builder()
                .icon(new Icon("lumo", "cross"))
                .click(l -> new DeleteCardCommand(context).execute())
                .build();
        var buttons = new ArrayList<>(context.getButtons());
        buttons.add(removeButton);
        var container = context.getCardContainer().add(context.getComponent(), buttons);
        context.setContainer(container);
        context.getItem().setEnabled(false);
    }
}
