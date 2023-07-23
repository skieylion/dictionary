package com.dictionary.application.controller;

import com.dictionary.application.domain.*;
import com.dictionary.application.domain.dto.CardDTO;
import com.dictionary.application.domain.dto.TranscriptionDTO;
import com.dictionary.application.repository.SlotRepository;
import com.dictionary.application.service.CardService;
import com.dictionary.application.service.FilePropertyService;
import com.dictionary.application.service.Navigator;
import com.dictionary.application.service.validator.CardBoxValidator;
import com.dictionary.application.view.CardBox;
import com.dictionary.application.view.CenterVerticalLayout;
import com.dictionary.application.view.CustomButton;
import com.dictionary.application.view.CustomVerticalLayout;
import com.dictionary.application.view.SlotBox;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import javax.annotation.security.PermitAll;
import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Route(value = "/cards/:cardId/editor", layout = Home.class)
@PermitAll
public class Editor extends CenterVerticalLayout implements RouterLayout, HasUrlParameter<String> {

    private final CardService cardService;
    private final SlotRepository slotRepository;
    private final FilePropertyService filePropertyService;
    private final SlotBox slotBox;
    private final Button buttonSave;
    private final VerticalLayout layout;

    {
        setAlignItems(Alignment.CENTER);
        layout = new VerticalLayout();
        layout.setWidth(Size.PERCENT_50);
    }

    public Editor(CardService cardService, SlotRepository slotRepository, FilePropertyService filePropertyService) {
        this.cardService = cardService;
        this.slotRepository = slotRepository;
        this.filePropertyService = filePropertyService;
        this.slotBox = new SlotBox(slotRepository.findAll());
        buttonSave = CustomButton.builder().name("save").width(Size.PERCENT_100).build();
    }

    private long getCardIdFromRouteParameters(BeforeEvent event) {
        RouteParameters parameters = event.getRouteParameters();
        return Long.parseLong(parameters.get("cardId").orElseThrow(EntityNotFoundException::new));
    }
    
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        Card card = cardService.findCardById(getCardIdFromRouteParameters(event));
        slotBox.selectSlots(card.getSlots());
        var cardBox = new CardBox();
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setExpression(card.getExpression());
        cardDTO.setExplanation(card.getDefinition());
        cardDTO.setExamples(card.getExamples().stream()
                .map(Example::getText)
                .collect(Collectors.toList()));
        cardDTO.setTranscriptions(card.getTranscriptions().stream()
                .map(transcription -> {
                    TranscriptionDTO transcriptionDTO = new TranscriptionDTO();
                    transcriptionDTO.setValue(transcription.getValue());
                    transcriptionDTO.setVariant(transcription.getVariant());
                    Optional.ofNullable(transcription.getFileProperty()).stream()
                            .map(filePropertyService::findByFileProperty)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .forEach(transcriptionDTO::setFile);
                    return transcriptionDTO;
                }).collect(Collectors.toList()));
        cardDTO.setTranslation(card.getTranslate());
        Optional.ofNullable(card.getFileProperty()).stream()
                .map(filePropertyService::findByFileProperty)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(PictureFile::new)
                .forEach(cardDTO::setPictureFile);
        cardBox.setCard(cardDTO);
        buttonSave.addClickListener(l -> clickButton(card.getId(), cardBox));
        layout.add(cardBox);
        layout.add(slotBox);
        layout.add(CustomVerticalLayout.builder().width(Size.PERCENT_100).add(buttonSave).build());
        add(layout);
    }

    private void clickButton(long cardId, CardBox cardBox) {
        if (new CardBoxValidator(cardBox).validate()) {
            CardDTO cardDTO = cardBox.getCard();
            cardDTO.setId(cardId);
            cardService.update(cardDTO, slotBox.getSelectedSlots());
            openDialog("The card is created");
            Navigator.WRITER.refresh();
        }
    }

    private void openDialog(String message) {
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setText(message);
        dialog.open();
    }


}