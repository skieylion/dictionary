package com.dictionary.web.service;

import com.dictionary.web.domain.CardContext;
import com.dictionary.web.domain.CardExampleContext;
import com.dictionary.web.domain.CardExplanationContext;
import com.dictionary.web.domain.CardImageContext;
import com.dictionary.web.domain.CardSoundContext;
import com.dictionary.web.view.StandardDefaultPicture;
import com.dictionary.web.view.box.SoundBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public final class CardValidator {
    private CardValidator() {
    }

    public static boolean validate(Collection<CardContext> contexts) {
        if (!Objects.nonNull(contexts) || contexts.isEmpty()) {
            Notification.show("this card is empty");
            return false;
        }
        for (var context : contexts) {
            if ((context instanceof CardImageContext && !validate((CardImageContext) context))
                    | (context instanceof CardExampleContext && !validate((CardExampleContext) context))
                    | (context instanceof CardSoundContext && !validate((CardSoundContext) context))
                    | (context instanceof CardExplanationContext
                    && !validate((CardExplanationContext) context))) {
                return false;
            }
        }
        return true;
    }

    private static boolean validate(CardImageContext context) {
        if (Objects.nonNull(context.getPicture())
                && Objects.nonNull(context.getPicture().getPictureFile())
                && !(context.getPicture() instanceof StandardDefaultPicture)) {
            return true;
        }
        Notification.show("The image is empty");
        return false;
    }

    private static boolean validate(CardExampleContext context) {
        var emptyTextFields =
                context.getExampleBox().getTextFieldsOfExamples().stream()
                        .filter(textField -> textField.getValue().isEmpty())
                        .collect(Collectors.toList());
        emptyTextFields.forEach(
                textField -> {
                    textField.setInvalid(true);
                    textField.setErrorMessage("The field is empty");
                });
        return !emptyTextFields.isEmpty();
    }

    private static boolean validate(CardSoundContext context) {
        var soundBox = context.getSoundBox();
        AtomicBoolean bool = new AtomicBoolean(true);
        soundBox.getSounds().stream()
                .map(SoundBox.Sound::getTranscription)
                .filter(textField -> textField.getValue().isBlank())
                .forEach(textField -> {
                    textField.setInvalid(true);
                    textField.setErrorMessage("The field is empty");
                    bool.set(false);
                });
        return bool.get();
    }

    private static boolean validate(CardExplanationContext context) {
        boolean isOk = !context.getTextArea().getValue().isBlank();
        if (!isOk) {
            context.getTextArea().setInvalid(true);
            context.getTextArea().setErrorMessage("The field is empty");
        }

        return isOk;
    }
}
