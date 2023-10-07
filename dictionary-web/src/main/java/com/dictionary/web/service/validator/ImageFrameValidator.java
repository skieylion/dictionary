package com.dictionary.web.service.validator;

import com.dictionary.web.view.PictureLoader;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

public class ImageFrameValidator implements Validator<PictureLoader> {

    @Override
    public ValidationResult apply(PictureLoader pictureLoader, ValueContext context) {

        if ("image-default.jpg".equals(pictureLoader.getPicture().getFullName())
                || (!(pictureLoader.getPicture().getBytes().length > 0))) {
            return ValidationResult.error("Изображение не загружено");
        }

        return ValidationResult.ok();
    }
}
