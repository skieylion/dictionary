package com.dictionary.application.service.command;

import com.dictionary.application.domain.CardContext;

public abstract class CardCommand {

    final CardContext context;

    public CardCommand(CardContext context) {
        this.context = context;
    }

    public abstract void execute();
}
