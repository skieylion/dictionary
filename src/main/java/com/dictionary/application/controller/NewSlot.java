package com.dictionary.application.controller;

import com.dictionary.application.domain.Size;
import com.dictionary.application.repository.batis.SlotStatRepository;
import com.dictionary.application.service.Navigator;
import com.dictionary.application.service.SlotService;
import com.dictionary.application.service.mapper.SlotMapper;
import com.dictionary.application.view.ButtonMini;
import com.dictionary.application.view.CardContainer;
import com.dictionary.application.view.CustomButton;
import com.dictionary.application.view.ImageSearcher;
import com.dictionary.application.view.PictureLoader;
import com.dictionary.application.view.VerticalHeaderLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.select.SelectVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoIcon;
import org.hibernate.Session;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route(value = "/slots/new", layout = Home.class)
@PermitAll
public class NewSlot extends VerticalLayout implements RouterLayout {
    private final SlotService slotService;
    private final Session session;
    private final SlotMapper slotMapper;
    private final SlotStatRepository slotStatRepository;
    private final Button saveButton;
    private final PictureLoader pictureLoader;

    {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setWidthFull();
    }

    private final VerticalLayout layout;
    private final TextField textField;

    public NewSlot(SlotService slotService, Session session, SlotMapper slotMapper, SlotStatRepository slotStatRepository) {
        this.slotService = slotService;
        this.session = session;
        this.slotMapper = slotMapper;
        this.slotStatRepository = slotStatRepository;
        Navigator.NEW_SLOT.select();
        saveButton = CustomButton.builder().name("save").width(Size.PERCENT_100).build();
        pictureLoader = new PictureLoader();
        pictureLoader.setWidth(Size.PERCENT_100);
        textField = new TextField();
        textField.setWidthFull();
        textField.setPlaceholder("name ...");
        layout = new VerticalLayout();
        layout.setWidth(Size.PERCENT_50);
        draw();
    }

    private void draw() {
        removeAll();
        layout.add(textField);
        layout.add(new VerticalHeaderLayout("image", pictureLoader));
        layout.add(saveButton);


        add(layout);
        saveButton.addClickListener(l -> clickButton());
    }

    private void clickButton() {

        slotService.insertSlot(textField.getValue(), pictureLoader.getPictureFile().orElse(null));
        UI.getCurrent().navigate(NewSlot.class);
    }


}