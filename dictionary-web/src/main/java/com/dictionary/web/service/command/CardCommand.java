package com.dictionary.web.service.command;

import com.dictionary.web.domain.CardContext;

public abstract class CardCommand {

    final CardContext context;

    public CardCommand(CardContext context) {
        this.context = context;
    }

    public abstract void execute();
}
