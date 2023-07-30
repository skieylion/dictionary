package com.dictionary.application.service;

import com.dictionary.application.domain.CardContext;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;

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
        });
    }

    @Override
    public void execute() {
        dialog.open();
    }
}
