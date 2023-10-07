package com.dictionary.web.controller;

import com.dictionary.web.domain.Card;
import com.dictionary.web.domain.CardRemember;
import com.dictionary.web.domain.Example;
import com.dictionary.web.domain.Size;
import com.dictionary.web.repository.CardRememberRepository;
import com.dictionary.web.service.FlashcardTrainer;
import com.dictionary.web.service.SlotService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.PermitAll;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Route(value = "/slots/:slotId/student", layout = Home.class)
@AllArgsConstructor
@PermitAll
public class Student extends VerticalLayout implements RouterLayout, HasUrlParameter<String> {

    private static final Integer SIZE = 2;
    private final SlotService slotService;
    private final Example exampleDefault = new Example();
    private final VerticalLayout layout = new VerticalLayout();
    private final CardRememberRepository cardRememberRepository;


    {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        exampleDefault.setText("...");
        setWidthFull();
        layout.setAlignItems(Alignment.CENTER);
        layout.setWidth(Size.PERCENT_50);
        add(layout);
    }

    private List<Card> getDeadlineCardsBySlotId(Long slotId) {
        return slotService.findById(slotId).getCards().stream()
                .filter(card -> Objects.nonNull(card.getCardRemember()))
                .filter(card -> {
                    long eventTime = card.getCardRemember().getEventDate().toEpochSecond(ZoneOffset.UTC);
                    long currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
                    return currentTime > eventTime;
                })
                .limit(Student.SIZE)
                .collect(Collectors.toList());
    }

    private List<Card> getNewCardsBySlotId(Long slotId) {
        return slotService.findById(slotId).getCards().stream()
                .filter(card -> !Objects.nonNull(card.getCardRemember()))
                .filter(card -> !card.isState())
                .limit(Student.SIZE)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        setAlignItems(Alignment.CENTER);
        RouteParameters parameters = event.getRouteParameters();
        Long id = Long.valueOf(parameters.get("slotId").orElseThrow(EntityNotFoundException::new));
        var deadlineCards = getDeadlineCardsBySlotId(id);
        List<Card> cards = deadlineCards.size() > 0 ? deadlineCards : getNewCardsBySlotId(id);
        layout.removeAll();
        layout.add(new FlashcardTrainer(cards, () -> {
            cards.stream().map(card -> cardRememberRepository.findByCardId(card.getId()).orElseGet(() -> {
                var cardRemember = new CardRemember();
                cardRemember.setCounter(0);
                cardRemember.setEventDate(LocalDateTime.now());
                cardRemember.setCard(card);
                return cardRemember;
            })).peek(cardRemember -> {
                cardRemember.setEventDate(LocalDateTime.now());
                cardRemember.setCounter(cardRemember.getCounter() + 1);
            }).forEach(cardRememberRepository::save);
            UI.getCurrent().navigate(Slots.class);
        }));
    }
}