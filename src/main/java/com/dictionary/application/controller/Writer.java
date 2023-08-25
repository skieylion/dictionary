package com.dictionary.application.controller;

import com.dictionary.application.domain.dto.CardDTO;
import com.dictionary.application.service.command.EditCardCommand;
import com.dictionary.application.service.Navigator;
import com.dictionary.application.service.UnsplashService;
import com.dictionary.application.service.handler.context.CardContextExampleHandler;
import com.dictionary.application.service.handler.context.CardContextExplanationHandler;
import com.dictionary.application.service.handler.context.CardContextImageHandler;
import com.dictionary.application.service.handler.context.CardContextSoundHandler;
import com.dictionary.application.service.validator.CardBoxValidator;
import com.dictionary.application.view.*;
import com.dictionary.application.domain.*;
import com.dictionary.application.repository.SlotRepository;
import com.dictionary.application.service.command.CardService;
import com.dictionary.application.view.box.CardBox;
import com.dictionary.application.view.box.ExampleBox;
import com.dictionary.application.view.box.SoundBox;
import com.dictionary.application.view.button.ButtonMini;
import com.dictionary.application.view.button.CustomButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
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

import javax.annotation.security.PermitAll;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

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
        //new ImageSearcher()
        MenuBarWrapper menuBarWrapper = new MenuBarWrapper("add");

        var cardContextImageHandler = new CardContextImageHandler();
        var cardContextExampleHandler = new CardContextExampleHandler();
        var cardContextSoundHandler = new CardContextSoundHandler();

        cardContextImageHandler.setNext(cardContextExampleHandler);
        cardContextExampleHandler.setNext(cardContextSoundHandler);
        cardContextSoundHandler.setNext(new CardContextExplanationHandler());

        cardContextImageHandler.handle(cardContainer, menuBarWrapper);


        var save = new Button("save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        var footerLayout = new HorizontalLayout(menuBarWrapper, save);
        footerLayout.setWidthFull();
        footerLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        footerLayout.setAlignItems(Alignment.CENTER);
        cardContainer.setFooter(footerLayout);
        //cardContainer.add(picture, Arrays.asList(b11, b22));
        //cardContainer.add(new Span("22222222222"), new ArrayList<>());
        //cardContainer.add(new Span("sdfdsf"), new ArrayList<>());
        layout.add(cardContainer);
        save.addClickListener(listener -> clickButton(slotId));
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