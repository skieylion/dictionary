package com.dictionary.application.service.validator;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

public class NotNullValidator implements Validator<String> {

    @Override
    public ValidationResult apply(String value, ValueContext context) {
        if (value != null && !value.equals("")) {
            return ValidationResult.ok();
        }
        return ValidationResult.error("Значение не может быть пустым");
    }
}
