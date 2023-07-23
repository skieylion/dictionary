package com.dictionary.application.controller;

import com.dictionary.application.domain.Example;
import com.dictionary.application.domain.PictureFile;
import com.dictionary.application.domain.Slot;
import com.dictionary.application.repository.FilePropertyRepository;
import com.dictionary.application.repository.SlotRepository;
import com.dictionary.application.service.CardService;
import com.dictionary.application.service.FilePropertyService;
import com.dictionary.application.view.ButtonMini;
import com.dictionary.application.view.CardContainer;
import com.dictionary.application.view.CardMiniReader;
import com.dictionary.application.view.ImageSearcher;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;


@Route(value = "/slots", layout = Home.class)
@AllArgsConstructor
@PermitAll
public class Cards extends FlexLayout implements RouterLayout, HasUrlParameter<Long> {

    private final CardService cardService;
    private final SlotRepository slotRepository;
    private final FilePropertyService filePropertyService;
    private final Example exampleDefault = new Example();

    {
        setFlexWrap(FlexLayout.FlexWrap.WRAP);
        setAlignItems(Alignment.CENTER);
        exampleDefault.setText("...");
    }

    @Override
    @Transactional
    public void setParameter(BeforeEvent event, Long id) {
        slotRepository.findById(id).orElse(new Slot()).getCards().stream()
                .map(card -> {
                    var cardMiniReader = new CardMiniReader(card.getExpression());
                    cardMiniReader.setText(card.getExamples().stream()
                            .map(Example::getText)
                            .findAny()
                            .orElse(Optional.ofNullable(card.getDefinition())
                                    .orElse("...")));
                    cardMiniReader.getButtonView().addClickListener(l -> UI.getCurrent().navigate(Reader.class, card.getId()));
                    cardMiniReader.getButtonEdit().addClickListener(l -> UI.getCurrent().navigate("/cards/" + card.getId() + "/editor"));
                    Optional.ofNullable(card.getFileProperty()).stream()
                            .map(filePropertyService::findByFileProperty)
                            .findFirst()
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .map(PictureFile::new)
                            .ifPresent(cardMiniReader::setPictureFile);
                    cardMiniReader.getButtonRemove().addClickListener(l -> {
                        cardService.deleteCard(card.getId());
                        UI.getCurrent().navigate(Cards.class, id);
                    });
                    return cardMiniReader;
                })
                .peek(cardMiniReader -> cardMiniReader.getStyle().set("margin", "10px"))
                .forEach(this::add);
    }
}