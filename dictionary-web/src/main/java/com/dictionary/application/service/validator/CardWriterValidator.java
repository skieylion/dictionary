package com.dictionary.application.service.validator;

import com.dictionary.application.domain.AudioItem;
import com.dictionary.application.domain.ElementType;
import com.dictionary.application.domain.ExampleItem;
import com.dictionary.application.domain.ListBoxItem;
import com.dictionary.application.view.box.AudioListBox;
import com.dictionary.application.view.box.ExampleListBox;
import com.dictionary.application.view.PictureLoader;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class CardWriterValidator {

    private final Map<ElementType, Binder<?>> binders = new HashMap<>();

    public static CardWriterValidatorBuilder builder() {
        return new CardWriterValidatorBuilder();
    }

    public boolean isValid(Set<ElementType> items) {
        return binders.keySet().stream()
                .filter(item -> items.contains(item) || item == ElementType.EXPRESSION)
                .map(binders::get)
                .peek(Binder::validate)
                .allMatch(binder -> {
                    boolean result = binder.isValid();
                    if (binders.size() > 0) {
                    }
                    return result;
                });
    }

    public static class CardWriterValidatorBuilder {

        private final CardWriterValidator cardWriterValidator = new CardWriterValidator();

        public CardWriterValidatorBuilder expression(TextField expression) {
            Binder<TextField> binderExpression = new Binder<>();
            binderExpression.forField(expression)
                    .withValidator(new NotNullValidator())
                    .bind(TextField::getValue, TextField::setValue);
            cardWriterValidator.binders.put(ElementType.EXPRESSION, binderExpression);
            return this;
        }

        public CardWriterValidatorBuilder explanation(TextArea explanation) {
            Binder<TextArea> binderExplanation = new Binder<>();
            binderExplanation.forField(explanation)
                    .withValidator(new NotNullValidator())
                    .bind(TextArea::getValue, TextArea::setValue);
            cardWriterValidator.binders.put(ElementType.EXPLANATION, binderExplanation);
            return this;
        }

        public CardWriterValidatorBuilder exampleList(ExampleListBox exampleList) {
            Consumer<ListBoxItem> consumer = fragmentItem -> {
                ExampleItem item = (ExampleItem) fragmentItem;
                Binder<TextField> binderExampleList = new Binder<>();
                binderExampleList.forField(item.getTextField())
                        .withValidator(new NotNullValidator())
                        .bind(TextField::getValue, TextField::setValue);
                cardWriterValidator.binders.put(ElementType.EXAMPLES, binderExampleList);
            };
            exampleList.addPlusListener(consumer);
            return this;
        }

        public CardWriterValidatorBuilder audio(AudioListBox audioListBox) {
            Consumer<ListBoxItem> consumer = fragmentItem -> {
                AudioItem item = (AudioItem) fragmentItem;
                Binder<AudioItem> binderAudio = new Binder<>();
                binderAudio.withValidator(new AudioItemValidator());
                binderAudio.setBean(item);
                binderAudio.setValidationStatusHandler(statusChange -> {
                    item.getLayout().getStyle().set("border", "0px solid red");
                    if (!binderAudio.isValid()) {
                        item.getLayout().getStyle().set("border", "1px solid red");
                    }
                });
                cardWriterValidator.binders.put(ElementType.AUDIO, binderAudio);
            };
            audioListBox.addPlusListener(consumer);
            return this;
        }

        public CardWriterValidatorBuilder imageFrame(PictureLoader pictureLoader) {
            Binder<PictureLoader> binderImage = new Binder<>();
            binderImage.withValidator(new ImageFrameValidator());
            binderImage.setBean(pictureLoader);
            binderImage.setValidationStatusHandler(statusChange -> {
                pictureLoader.getStyle().set("border", "0px solid red");
                if (!binderImage.isValid()) {
                    pictureLoader.getStyle().set("border", "1px solid red");
                }
            });
            cardWriterValidator.binders.put(ElementType.IMAGE, binderImage);
            return this;
        }

        public CardWriterValidator build() {
            return cardWriterValidator;
        }
    }

}
