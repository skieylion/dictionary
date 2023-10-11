package com.dictionary.web.service;

import com.dictionary.core.domain.Card;
import com.dictionary.core.domain.Example;
import com.dictionary.web.view.ButtonController;
import com.dictionary.web.view.CardReader;
import com.dictionary.web.view.Picture;
import com.dictionary.web.view.StandardDefaultPicture;
import com.dictionary.web.view.button.CustomButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FlashcardReaderTrainer extends VerticalLayout {
    {
        setWidthFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private final Button academyButton;
    private final Button prevButton;
    private final Button nextButton;

    {
        academyButton = CustomButton.builder().icon(VaadinIcon.ACADEMY_CAP).build();
        academyButton.setEnabled(false);
        prevButton = CustomButton.builder().name("prev").build();
        nextButton = CustomButton.builder().name("next").build();
    }

    private final ButtonController buttonController;

    public FlashcardReaderTrainer(List<Card> cards, Runnable runnable) {
        var cardReaderList =
                cards.stream()
                        .map(FlashcardReaderTrainer::createCardReaderFromCard)
                        .collect(Collectors.toList());
        Slider slider = new Slider(cards.size());
        buttonController = createButtonController(slider, runnable);
        slider.change(
                (dir, index) -> {
                    addCardReader(cardReaderList.get(index));
                    setButtonState(index, cardReaderList.size());
                });
        slider.init();
    }

    private void setButtonState(int index, int size) {
        if (index == size - 1) {
            academyButton.setEnabled(true);
        }
        prevButton.setEnabled(true);
        nextButton.setEnabled(true);
        if (index == 0) {
            prevButton.setEnabled(false);
        }
        if (index == size - 1) {
            nextButton.setEnabled(false);
        }
    }

    private void addCardReader(CardReader cardReader) {
        removeAll();
        add(cardReader);
        add(buttonController);
    }

    private ButtonController createButtonController(Slider slider, Runnable runnable) {
        return ButtonController.builder()
                .button(prevButton, button -> slider.prev())
                .button(nextButton, button -> slider.next())
                .button(
                        academyButton,
                        button -> {
                            runnable.run();
                        })
                .build();
    }

    private static CardReader createCardReaderFromCard(Card card) {
        var file = card.getPictureFile();
        var picture = Objects.nonNull(file) ? new Picture(file) : new StandardDefaultPicture();
        return new CardReader(
                card.getExpression(),
                picture.getPictureFile(),
                card.getDefinition(),
                new ArrayList<>(card.getTranscriptions()),
                card.getExamples().stream().map(Example::getText).collect(Collectors.toList()));
    }
}
