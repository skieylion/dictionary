package com.dictionary.application.service.command;

import com.dictionary.application.domain.CardContext;

public class BackwardCardCommand extends CardCommand {
    public BackwardCardCommand(CardContext context) {
        super(context);
    }

    @Override
    public void execute() {

    }
}
