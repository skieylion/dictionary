package com.dictionary.application.service.validator;

import com.dictionary.application.view.CardBox;
import com.vaadin.flow.component.notification.Notification;


public class CardBoxValidator implements Validate {

    private static final String ERROR_MESSAGE = "The field is not correct";
    private final CardBox cardBox;

    public CardBoxValidator(CardBox cardBox) {
        this.cardBox = cardBox;
    }

    @Override
    public boolean validate() {
        if (!validateExpression() || !validateExample() || !validateTranscription()) {
            return false;
        }
        if (!validateTrainers()) {
            Notification.show("The card is not correct");
            return false;
        }
        return true;
    }

    private boolean validateTrainers() {
        if (!(isAssociationTrainerValid() | isExampleTrainerValid() | isSoundTrainerValid())) {
            if (!isAssociationTrainerValid()) {
                cardBox.getPictureLoader().setInvalid(true);
            } else if (!isExampleTrainerValid()) {
                var example = cardBox.getExampleListBox().getExamples().get(0);
                example.getTextField().setInvalid(true);
                example.getTextField().setErrorMessage(ERROR_MESSAGE);
            } else if (!isSoundTrainerValid()) {
                var audioItem = cardBox.getAudioListBox().getAudioItems().get(0);
                audioItem.getLayout().getStyle().set("border-color", "red");
            }
            return false;
        }
        return true;
    }

    private boolean validateExpression() {
        if (cardBox.getExpression().getValue().isBlank()) {
            cardBox.getExpression().setInvalid(true);
            cardBox.getExpression().setErrorMessage(ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validateExample() {
        int exampleCount = cardBox.getExampleListBox().getExamples().size();
        for (var example : cardBox.getExampleListBox().getExamples()) {
            if (example.getTextField().getValue().isBlank() && exampleCount > 1) {
                example.getTextField().setInvalid(true);
                example.getTextField().setErrorMessage(ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private boolean validateTranscription() {
        int audioCount = cardBox.getAudioListBox().getAudioItems().size();
        for (var audioItem : cardBox.getAudioListBox().getAudioItems()) {
            audioItem.getLayout().getStyle().set("border-color", "#504F51");
            if (audioItem.getFile().isEmpty() && audioCount > 1) {
                audioItem.getLayout().getStyle().set("border-color", "red");
                return false;
            }
        }
        return true;
    }

    private boolean isAssociationTrainerValid() {
        return !cardBox.getPictureLoader().isStandardPicture();
    }

    private boolean isExampleTrainerValid() {
        for (var example : cardBox.getExampleListBox().getExamples()) {
            if (!example.getTextField().getValue().isBlank()) {
                return true;
            }
        }
        return false;
    }

    private boolean isSoundTrainerValid() {
        for (var audioItem : cardBox.getAudioListBox().getAudioItems()) {
            if (audioItem.getFile().isPresent()) {
                return true;
            }
        }
        return false;
    }
}
