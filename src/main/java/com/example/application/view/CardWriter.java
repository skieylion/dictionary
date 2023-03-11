package com.example.application.view;

import com.example.application.domain.CardVariant;
import com.example.application.domain.Size;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class CardWriter extends FullVerticalLayout {
    private static final String CAROUSEL_HEIGHT = "400px";
    private final Map<String, Runnable> views = new HashMap<>();
    private final Map<String, Supplier<Boolean>> rules = new HashMap<>();

    private final Expression expression;
    private final Carousel carousel;
    private final FullTextArea textArea;
    private final ExampleList exampleList;
    private final AudioList audioList;
    private final SlotSelect slotSelect;
    private final SelectVariantCard selectVariantCard;

    {
        expression = new Expression(null);
        carousel = new Carousel(CAROUSEL_HEIGHT);
        textArea = FullTextArea.createInstance().placeholder("definition");
        exampleList = new ExampleList();
        audioList = new AudioList();
        slotSelect = new SlotSelect();
        selectVariantCard = new SelectVariantCard(CardVariant.BASIC);
    }


    public CardWriter() {
        setWidth(Size.PERCENT_50);
        selectVariantCard.change(value -> {
            removeAll();
            add(selectVariantCard);
            views.get(value).run();
        });
        add(selectVariantCard);
        views.get(CardVariant.BASIC).run();
        add(ButtonMenu.createInstance()
                .button("clean", () -> {
                })
                .button("create", () -> {
                }));
    }

    {
        views.put(CardVariant.FULL, () -> {
            add(expression);
            add(carousel);
            add(textArea);
            add(exampleList);
            add(audioList);
            add(slotSelect);
        });

        views.put(CardVariant.BASIC, () -> {
            add(expression);
            add(exampleList);
            Accordion accordion = new Accordion();
            accordion.close();
            accordion.setWidth(Size.PERCENT_100);
            accordion.add("image", carousel);
            accordion.add("definition", textArea);
            accordion.add("audio", audioList);
            add(accordion);
            add(slotSelect);
        });
    }

    {
        rules.put(CardVariant.BASIC, () -> !StringUtils.isBlank(expression.getText())
                && exampleList.getExamples().size() > 0
                && exampleList.getExamples()
                .stream()
                .noneMatch(StringUtils::isBlank));
        //rules.put(CardVariant.FULL,()->);
    }
}
