package com.dictionary.web.view.exercise;

import com.dictionary.core.domain.PictureFile;
import com.dictionary.core.domain.Size;
import com.dictionary.web.domain.Trainer;
import com.dictionary.web.service.validator.ImageExerciseValidator;
import com.dictionary.web.view.Picture;
import com.dictionary.web.view.StandardDefaultPicture;
import com.dictionary.web.view.TextRecordField;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;

import java.util.Objects;
import java.util.function.Predicate;

public class AssociationTrainer extends Trainer {
    {
        setWidthFull();
        setSpacing(false);
        setPadding(false);
        setMargin(false);
        setAlignItems(Alignment.CENTER);
    }

    private final TextField field;

    public AssociationTrainer(PictureFile file, String explanation, Predicate<String> predicate) {
        var picture = createPictureByFile(file);
        var text = createExplanation(explanation);
        field = createTextField();
        var subWrapper = createSubWrapper(createSubLayout(text, field));
        add(picture);
        add(subWrapper);
        setBinder(field, predicate);
    }

    public AssociationTrainer(PictureFile file, String explanation) {
        this(file, explanation, s -> true);
    }

    @Override
    public String getValue() {
        return field.getValue();
    }

    private static Picture createPictureByFile(PictureFile pictureFile) {
        Picture picture = Objects.nonNull(pictureFile) ? new Picture(pictureFile) : new StandardDefaultPicture();
        picture.getStyle().set("border-top-left-radius", "10px");
        picture.getStyle().set("border-top-right-radius", "10px");
        picture.getStyle().set("border", "3px solid #504F51");
        picture.setWidthFull();
        picture.setMaxHeight(Size.PX_400);
        return picture;
    }

    private static Span createExplanation(String explanation) {
        var span = new Span(explanation);
        span.getStyle().set("display", "flex");
        span.getStyle().set("justify-content", "center");
        span.getStyle().set("align-items", "center");
        return span;
    }

    private static Div createSubWrapper(VerticalLayout layout) {
        var subWrapper = new Div();
        subWrapper.getStyle().set("border-bottom-left-radius", "10px");
        subWrapper.getStyle().set("border-bottom-right-radius", "10px");
        subWrapper.getStyle().set("border-right", "3px solid #504F51");
        subWrapper.getStyle().set("border-left", "3px solid #504F51");
        subWrapper.getStyle().set("border-bottom", "3px solid #504F51");
        subWrapper.setWidthFull();
        subWrapper.add(layout);
        return subWrapper;
    }

    private static VerticalLayout createSubLayout(Span explanation, TextField field) {
        var subLayout = new VerticalLayout();
        subLayout.setWidthFull();
        subLayout.setAlignItems(Alignment.CENTER);
        var h = new HorizontalLayout(field);
        h.setAlignItems(Alignment.CENTER);
        h.setWidth(Size.PERCENT_50);
        subLayout.add(explanation, h);
        return subLayout;
    }

    private static TextField createTextField() {
        var field = new TextRecordField();
        field.getStyle().set("font-size", "18px");
        field.setWidthFull();
        field.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        return field;
    }

    private static void setBinder(TextField field, Predicate<String> predicate) {
        Binder<TextField> binder = new Binder<>();
        binder.forField(field)
                .withValidator(new ImageExerciseValidator(predicate))
                .bind(TextField::getValue, TextField::setValue);
        field.addKeyPressListener(Key.ENTER, event -> {
            binder.validate();
        });
    }
}
