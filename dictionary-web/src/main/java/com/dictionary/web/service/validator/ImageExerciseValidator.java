package com.dictionary.web.service.validator;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

import java.util.function.Predicate;

public class ImageExerciseValidator implements Validator<String> {

    private final Predicate<String> predicate;

    public ImageExerciseValidator(Predicate<String> predicate) {
        this.predicate = predicate;
    }

    @Override
    public ValidationResult apply(String value, ValueContext context) {
        if (predicate.test(value)) {
            return ValidationResult.ok();
        }
        return ValidationResult.error("Your answer isn't correct");
    }
}
