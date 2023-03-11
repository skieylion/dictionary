package com.example.application.view;

import com.example.application.domain.Size;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Arrays;

public class SlotList extends VerticalLayout {


    Component createCardMini() {

        return new CardMiniReader("Car", "The car is very expensive").getComponent();
    }


    Component createCard() {
        SlotReader slotReader = new SlotReader("image-mini-default.png", "7/25/82");
        slotReader.getComponent().getStyle().set("margin", "10px");
        return slotReader.getComponent();
    }

    public SlotList() {

        setWidthFull();
        //setAlignItems(Alignment.STRETCH);
        //setAlignItems(Alignment);

        //setFlexWrap(FlexWrap.WRAP);
        //SlotCreator slotCreator = new SlotCreator();
        //slotCreator.getComponent().getStyle().set("margin", "10px");
        //add(slotCreator.getComponent());

        this.setAlignItems(Alignment.CENTER);
        //setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setWidth(Size.PERCENT_100);

        add(CardReader.builder()
                .caption("Rainforest")
                .explanation("a forest in a tropical region of the world where it rains a lot. Rainforests are considered to be important environmental areas and many people want them to be protected by law")
                .picture()
                .examples(Arrays.asList("I work hard", "I work for ITFB"))
                .audio(Arrays.asList("w:ok", "w:ok"))
                .build());

    }
}
