package com.dictionary.application.view.exercise;

import com.dictionary.application.domain.AudioFile;
import com.dictionary.application.domain.Size;
import com.dictionary.application.domain.Trainer;
import com.dictionary.application.domain.Transcription;
import com.dictionary.application.view.AudioButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SoundTrainer extends Trainer {

    {
        setWidthFull();
        setAlignItems(Alignment.CENTER);
        getStyle().set("border-radius", "10px");
        getStyle().set("border", "3px solid #504F51");
    }

    private final TextField textField;

    public SoundTrainer(AudioFile audioFile, int maxLength) {
        AudioButton audioButton = new AudioButton(audioFile);

        textField = new TextField();
        textField.setMaxLength(maxLength);
        textField.getStyle().set("font-size", "18px");
        textField.setWidth(Size.PERCENT_50);
        textField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        add(audioButton);
        add(textField);
    }

    @Override
    public String getValue() {
        return textField.getValue();
    }


}
