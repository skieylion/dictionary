package com.dictionary.web.controller;

import com.dictionary.core.domain.Size;
import com.dictionary.core.repository.SlotStatRepository;
import com.dictionary.web.service.Navigator;
import com.dictionary.web.service.SlotService;
import com.dictionary.web.service.mapper.SlotMapper;
import com.dictionary.web.view.button.CustomButton;
import com.dictionary.web.view.PictureLoader;
import com.dictionary.web.view.layout.VerticalHeaderLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import org.hibernate.Session;

import javax.annotation.security.PermitAll;

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