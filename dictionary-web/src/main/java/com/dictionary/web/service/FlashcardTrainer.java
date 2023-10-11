package com.dictionary.web.service;

import com.dictionary.core.domain.AudioFile;
import com.dictionary.core.domain.Card;
import com.dictionary.core.domain.DefaultAudioFile;
import com.dictionary.core.domain.Example;
import com.dictionary.core.domain.Transcription;
import com.dictionary.web.domain.Trainer;
import com.dictionary.web.service.filter.AssociationTrainerCardFilter;
import com.dictionary.web.service.filter.CardFilter;
import com.dictionary.web.service.filter.CardSource;
import com.dictionary.web.service.filter.ExampleTrainerCardFilter;
import com.dictionary.web.service.filter.FilterUtils;
import com.dictionary.web.service.filter.SoundTrainerCardFilter;
import com.dictionary.web.view.exercise.AssociationTrainer;
import com.dictionary.web.view.exercise.ContextTrainer;
import com.dictionary.web.view.exercise.SoundTrainer;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FlashcardTrainer extends VerticalLayout {
    {
        setWidthFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private static final List<Function<Card, Trainer>> TRAINER_MAP =
            List.of(
                    FlashcardTrainer::createAssociationTrainer,
                    FlashcardTrainer::createContextTrainer,
                    FlashcardTrainer::createSoundTrainer);

    private static final List<Function<CardFilter, CardFilter>> FILTER_MAP =
            List.of(
                    AssociationTrainerCardFilter::new,
                    ExampleTrainerCardFilter::new,
                    SoundTrainerCardFilter::new);

    private int index;
    private final CardSource cardSource;
    private final Runnable excitable;

    public FlashcardTrainer(List<Card> cards, Runnable excitable) {
        this.excitable = excitable;
        cardSource = new CardSource(cards);
        index = 0;
        read(cardSource.apply());
    }

    private void read(List<Card> cards) {
        removeAll();
        add(new FlashcardReaderTrainer(cards, () -> train(cards)));
    }

    private void train(List<Card> cards) {
        CardFilter cardFilter = FILTER_MAP.get(index).apply(new CardSource(cards));
        List<Trainer> trainers =
                cardFilter.apply().stream()
                        .map(card -> TRAINER_MAP.get(index).apply(card))
                        .collect(Collectors.toList());
        if (trainers.size() > 0) {
            FlashcardExerciseTrainer trainer =
                    new FlashcardExerciseTrainer(
                            trainers,
                            wrongCards -> {
                                if (wrongCards.size() > 0) {
                                    read(wrongCards);
                                } else {
                                    nextOrFinish();
                                }
                            });
            removeAll();
            add(trainer);
        } else {
            nextOrFinish();
        }
    }

    private void nextOrFinish() {
        index++;
        if (index == cardSource.apply().size()) {
            if (Objects.nonNull(excitable)) {
                excitable.run();
            }
        } else {
            train(cardSource.apply());
        }
    }

    private static Trainer createAssociationTrainer(Card card) {
        Trainer trainer = new AssociationTrainer(card.getPictureFile(), card.getDefinition());
        trainer.setCard(card);
        trainer.setInspector((value) -> card.getExpression().equalsIgnoreCase(value));
        return trainer;
    }

    private static Trainer createContextTrainer(Card card) {
        Set<Example> examples = FilterUtils.examples(card.getExamples());
        List<Example> exampleList = new ArrayList<>(examples);
        Collections.shuffle(exampleList);
        String example = exampleList.size() > 0 ? exampleList.get(0).getText() : "";
        if (!example.contains("{")) {
            example =
                    example.replaceAll(card.getExpression(), String.format("{%s}", card.getExpression()));
        }
        Trainer trainer =
                new ContextTrainer(example, card.getDefinition(), card.getExpression().length());
        trainer.setCard(card);
        trainer.setInspector((value) -> card.getExpression().equalsIgnoreCase(value));
        return trainer;
    }

    private static Trainer createSoundTrainer(Card card) {
        List<Transcription> transcriptions = new ArrayList<>(card.getTranscriptions());
        Collections.shuffle(transcriptions);
        Trainer trainer = new SoundTrainer(new DefaultAudioFile(), card.getExpression().length());
        if (transcriptions.size() > 0) {
            var transcription = transcriptions.get(0);
            if (Objects.nonNull(transcription.getFile())) {
                trainer =
                        new SoundTrainer(new AudioFile(transcription.getFile()), card.getExpression().length());
            }
        }
        trainer.setCard(card);
        trainer.setInspector((value) -> card.getExpression().equalsIgnoreCase(value));
        return trainer;
    }
}
