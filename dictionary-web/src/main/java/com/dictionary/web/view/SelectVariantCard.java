package com.dictionary.web.view;

import com.dictionary.web.domain.CardVariant;
import com.dictionary.web.domain.Size;
import com.dictionary.web.view.layout.FullVerticalLayout;
import com.vaadin.flow.component.select.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SelectVariantCard extends FullVerticalLayout {

    private static final List<String> cardVariants;

    static {
        cardVariants = new ArrayList<>();
        cardVariants.add(CardVariant.BASIC);
        cardVariants.add(CardVariant.FULL);
    }

    private final Select<String> select = new Select<>();

    public SelectVariantCard(String cardVariant) {
        select.setItems(cardVariants);
        select.setValue(cardVariant);
        select.setWidth(Size.PERCENT_100);
        add(select);
    }

    public void change(Consumer<String> consumer) {
        select.addValueChangeListener(event -> consumer.accept(event.getValue()));
    }

}
