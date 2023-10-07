package com.dictionary.web.service;

import com.dictionary.web.domain.Card;
import com.dictionary.web.domain.Trainer;
import com.dictionary.web.view.ButtonController;
import com.dictionary.web.view.button.CustomButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FlashcardExerciseTrainer extends VerticalLayout {
    {
        setWidthFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private final AtomicInteger currentIndex = new AtomicInteger(0);
    private final List<Trainer> trainers;
    private final ButtonController buttonController;
    private final Slider slider;
    private final List<Trainer> wrongCards = new ArrayList<>();

    public <T extends Trainer> FlashcardExerciseTrainer(List<Trainer> trainers, Consumer<List<Card>> consumer) {
        this.trainers = trainers;
        slider = new Slider(trainers.size());
        Button check = CustomButton.builder().name("check").build();
        Button skip = CustomButton.builder().name("skip").theme(ButtonVariant.LUMO_ERROR).build();
        buttonController = ButtonController.builder()
                .button(skip, button -> skip(trainers.size(), trainers.get(currentIndex.get()), consumer))
                .button(check, button -> check(trainers.size(), trainers.get(currentIndex.get()), consumer))
                .build();
        slider.change((dir, index) -> {
            currentIndex.set(index);
            addExercise();
        });
        slider.init();
    }

    private void skip(int size, Trainer trainer, Consumer<List<Card>> consumer) {
        wrongCards.add(trainer);
        nextOrClose(size, consumer);
    }

    private void check(int size, Trainer trainer, Consumer<List<Card>> consumer) {
        if (trainer.inspect()) {
            Notification.show("It's correct");
            nextOrClose(size, consumer);
        } else {
            Notification.show("It's wrong");
        }
    }

    private void nextOrClose(int size, Consumer<List<Card>> consumer) {
        if (currentIndex.get() == size - 1) {
            consumer.accept(wrongCards.stream().map(Trainer::getCard).collect(Collectors.toList()));
        } else {
            slider.next();
        }
    }

    private void addExercise() {
        removeAll();
        add(trainers.get(currentIndex.get()));
        add(buttonController);
    }
}
