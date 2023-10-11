package com.dictionary.web.controller;

import com.dictionary.core.domain.Example;
import com.dictionary.core.domain.PictureFile;
import com.dictionary.core.domain.Slot;
import com.dictionary.core.repository.SlotRepository;
import com.dictionary.web.service.FilePropertyService;
import com.dictionary.web.service.command.CardService;
import com.dictionary.web.view.CardMiniReader;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import java.util.Optional;
import javax.annotation.security.PermitAll;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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
                .map(
                        card -> {
                            var cardMiniReader = new CardMiniReader(card.getExpression());
                            cardMiniReader.setText(
                                    card.getExamples().stream()
                                            .map(Example::getText)
                                            .findAny()
                                            .orElse(Optional.ofNullable(card.getDefinition()).orElse("...")));
                            cardMiniReader
                                    .getButtonView()
                                    .addClickListener(l -> UI.getCurrent().navigate(Reader.class, card.getId()));
                            cardMiniReader
                                    .getButtonEdit()
                                    .addClickListener(
                                            l -> UI.getCurrent().navigate("/cards/" + card.getId() + "/editor"));
                            Optional.ofNullable(card.getFileProperty()).stream()
                                    .map(filePropertyService::findByFileProperty)
                                    .findFirst()
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .map(PictureFile::new)
                                    .ifPresent(cardMiniReader::setPictureFile);
                            cardMiniReader
                                    .getButtonRemove()
                                    .addClickListener(
                                            l -> {
                                                cardService.deleteCard(card.getId());
                                                UI.getCurrent().navigate(Cards.class, id);
                                            });
                            return cardMiniReader;
                        })
                .peek(cardMiniReader -> cardMiniReader.getStyle().set("margin", "10px"))
                .forEach(this::add);
    }
}
