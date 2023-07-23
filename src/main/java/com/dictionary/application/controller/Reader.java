package com.dictionary.application.controller;

import com.dictionary.application.domain.Card;
import com.dictionary.application.domain.Example;
import com.dictionary.application.domain.PictureFile;
import com.dictionary.application.domain.Size;
import com.dictionary.application.domain.Transcription;
import com.dictionary.application.repository.CardRepository;
import com.dictionary.application.service.FilePropertyService;
import com.dictionary.application.view.CardReader;
import com.dictionary.application.view.StandardDefaultPicture;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import lombok.AllArgsConstructor;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Route(value = "/cards", layout = Home.class)
@AllArgsConstructor
@PermitAll
public class Reader extends VerticalLayout implements RouterLayout, HasUrlParameter<Long> {
    private final CardRepository cardRepository;
    private final FilePropertyService filePropertyService;

    {
        setAlignItems(Alignment.CENTER);
        setWidthFull();
    }

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        Card card = cardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        card.getTranscriptions().stream()
                .filter(transcription -> Objects.nonNull(transcription.getFileProperty()))
                .forEach(transcription -> filePropertyService
                        .findByFileProperty(transcription.getFileProperty())
                        .ifPresent(transcription::setFile));
        CardReader cardReader = new CardReader(card.getExpression(),
                Optional.ofNullable(card.getFileProperty()).stream()
                        .map(filePropertyService::findByFileProperty)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(PictureFile::new)
                        .findFirst()
                        .orElse(new StandardDefaultPicture().getPictureFile()),
                card.getDefinition(),
                new ArrayList<>(card.getTranscriptions()),
                card.getExamples().stream()
                        .map(Example::getText)
                        .collect(Collectors.toList()));
        cardReader.setWidth(Size.PERCENT_50);
        add(cardReader);
    }
}
