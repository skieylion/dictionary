package com.dictionary.application.service.validator;

import com.dictionary.application.domain.AudioItem;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

public class AudioItemValidator implements Validator<AudioItem> {
    @Override
    public ValidationResult apply(AudioItem value, ValueContext context) {
        if (value.getAudioPlayButton().getFile().isEmpty()) {
            return ValidationResult.error("Аудиофайл не добавлен");
        }
        return ValidationResult.ok();
    }
}
