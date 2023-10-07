package com.dictionary.web.service.handler.context;

import com.dictionary.web.domain.CardContext;
import com.dictionary.web.domain.ContextContainer;
import com.dictionary.web.service.command.NewCardCommand;

import java.util.Optional;

public abstract class CardContextBaseCreator {

    private CardContextBaseCreator next;

    public void setNext(CardContextBaseCreator handler) {
        next = handler;
    }

    public final void handle(ContextContainer contextContainer) {
        CardContext context = createContext();
        context.setCardContainer(contextContainer.getContainer());
        context.setContextContainer(contextContainer);
        createMenuItem(context);
        Optional.ofNullable(next)
                .ifPresent(next -> next.handle(contextContainer));
    }

    private static void createMenuItem(CardContext context) {
        var item = context.getContextContainer().getMenu().addItem(context.getTitle());
        context.setItem(item);
        item.addClickListener(listener -> {
            new NewCardCommand(context).execute();
        });
    }

    abstract CardContext createContext();
}
