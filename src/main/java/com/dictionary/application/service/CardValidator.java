package com.dictionary.application.service;

import com.dictionary.application.domain.CardContext;
import com.dictionary.application.domain.CardExampleContext;
import com.dictionary.application.domain.CardExplanationContext;
import com.dictionary.application.domain.CardImageContext;
import com.dictionary.application.domain.CardSoundContext;
import com.dictionary.application.view.StandardDefaultPicture;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Deque;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CardValidator {
    public static boolean validate(Collection<CardContext> contexts) {
        if (!Objects.nonNull(contexts) || contexts.isEmpty()) {
            Notification.show("this card is empty");
            return false;
        }
        for (var context : contexts) {
            if ((context instanceof CardImageContext && !validate((CardImageContext) context))
                    | (context instanceof CardExampleContext && !validate((CardExampleContext) context))
                    | (context instanceof CardSoundContext && !validate((CardSoundContext) context))
                    | (context instanceof CardExplanationContext && !validate((CardExplanationContext) context))) {
                return false;
            }
        }
        return false;
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
        var emptyTextFields = context.getExampleBox().getTextFieldsOfExamples().stream()
                .filter(textField -> textField.getValue().isEmpty())
                .collect(Collectors.toList());
        emptyTextFields.forEach(textField -> {
            textField.setInvalid(true);
            textField.setErrorMessage("The field is empty");
        });
        return !emptyTextFields.isEmpty();
    }

    private static boolean validate(CardSoundContext context) {
        return true;
    }

    private static boolean validate(CardExplanationContext context) {
        return true;
    }

}
