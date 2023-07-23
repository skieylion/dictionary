package com.dictionary.application.controller;

import com.dictionary.application.domain.dto.CardDTO;
import com.dictionary.application.service.Navigator;
import com.dictionary.application.service.UnsplashService;
import com.dictionary.application.service.validator.CardBoxValidator;
import com.dictionary.application.view.*;
import com.dictionary.application.domain.*;
import com.dictionary.application.repository.SlotRepository;
import com.dictionary.application.service.CardService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.Command;

import javax.annotation.security.PermitAll;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

@Route(value = "/slots/:slotId/cards/writer", layout = Home.class)
@PermitAll
public class Writer extends VerticalLayout implements RouterLayout, HasUrlParameter<String> {
    {
        setAlignItems(Alignment.CENTER);
        setWidthFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private final CardService cardService;
    private final CardBox cardBox;
    private final Button buttonSave;
    //private final SlotBox slotBox;
    private final UnsplashService unsplashService;
    private final SlotRepository slotRepository;


    public Writer(CardService cardService, SlotRepository slotRepository, UnsplashService unsplashService) {
        this.cardService = cardService;
        this.unsplashService = unsplashService;
        this.slotRepository = slotRepository;
        cardBox = new CardBox();
        cardBox.getExpression().addValueChangeListener(event -> {
            var pictures = unsplashService.findPictures(event.getValue());
            cardBox.setPictures(pictures);
        });
        //slotBox = new SlotBox(slotRepository.findAll());
        buttonSave = CustomButton.builder().name("save").width(Size.PERCENT_100).build();
    }

    private void draw(long slotId) {
        removeAll();
        var layout = new VerticalLayout();
        layout.setWidth(Size.PERCENT_50);


        var tf = new TextField();
        tf.setWidthFull();
        CardContainer cardContainer = new CardContainer();
        cardContainer.setHeader(tf);
        var b1 = new Button("add ...");
        b1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        var b2 = new Button("save");
        b2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        var h = new HorizontalLayout(b1, b2);
        h.setWidthFull();
        h.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        h.setAlignItems(Alignment.CENTER);
        cardContainer.setFooter(h);

        var b11 = new ButtonMini(new Icon("lumo", "edit"));
        var b22 = new ButtonMini(new Icon("lumo", "cross"));
        List.of(ButtonMini.builder().build(), ButtonMini.builder().build());

        //new ImageSearcher()

        cardContainer.add(picture, Arrays.asList(b11, b22));
        cardContainer.add(new Span("22222222222"), new ArrayList<>());
        cardContainer.add(new Span("sdfdsf"), new ArrayList<>());


        var picture = new StandardDefaultPicture();
        picture.setWidth(Size.PX_700);
        picture.setHeight(Size.PX_450);

        MenuBarWrapper menuBarWrapper = new MenuBarWrapper("add");
        menuBarWrapper.add("image", CardContext.builder()
                .component(picture)
                .buttons(List.of(ButtonMini.builder()
                        .icon(new Icon("lumo", "edit"))
                        .build()))
                .build()).add("examples", CardContext.builder()
                .component(new StandardDefaultPicture())
                .build());


        layout.add(cardContainer);
        //layout.add(slotBox);
        layout.add(new VerticalLayout(buttonSave));
        buttonSave.addClickListener(listener -> clickButton(slotId));
        add(layout);
    }

    private void openDialog(String message) {
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setText(message);
        dialog.open();
    }

    private void clickButton(long slotId) {
        if (new CardBoxValidator(cardBox).validate()) {
            CardDTO cardDTO = cardBox.getCard();
            cardService.insert(cardDTO, slotRepository.findAllById(List.of(slotId)));
            openDialog("The card is created");
            Navigator.WRITER.refresh();
        }
    }


    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        RouteParameters parameters = event.getRouteParameters();
        long slotId = Long.parseLong(parameters.get("slotId").orElseThrow(EntityNotFoundException::new));
        Navigator.WRITER.select();
        draw(slotId);
    }
}