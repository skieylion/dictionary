package com.dictionary.application.service.command;

import com.dictionary.application.domain.CardContext;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;

import java.util.Optional;

public class DeleteCardCommand extends CardCommand {
    private final ConfirmDialog dialog;

    public DeleteCardCommand(CardContext context) {
        super(context);
        dialog = new ConfirmDialog();
        dialog.setText("Do you want to delete this component?");
        dialog.setCancelable(true);
        dialog.setConfirmText("Yes");
        dialog.addConfirmListener(event -> {
            context.getItem().setEnabled(true);
            context.getCardContainer().drop(context.getContainer().getLayout());
            Optional.ofNullable(context.getDefaultComponent())
                    .ifPresent(context::setComponent);
            Optional.ofNullable(context.getDefaultButtons())
                    .ifPresent(context::setButtons);
        });
    }

    @Override
    public void execute() {
        dialog.open();
    }
}
