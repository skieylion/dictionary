package com.dictionary.application.service.handler.context;

import com.dictionary.application.domain.CardContext;
import com.dictionary.application.view.CardContainer;
import com.dictionary.application.view.MenuBarWrapper;

import java.util.Optional;
import java.util.Queue;

public abstract class CardContextBaseHandler {

    private CardContextBaseHandler next;

    public void setNext(CardContextBaseHandler handler) {
        next = handler;
    }

    public final void handle(CardContainer container, MenuBarWrapper menu) {
        CardContext context = context();
        context.setCardContainer(container);
        menu.add(context.getTitle(), context);
        Optional.ofNullable(next)
                .ifPresent(next -> next.handle(container, menu));
    }

    abstract CardContext context();
}
