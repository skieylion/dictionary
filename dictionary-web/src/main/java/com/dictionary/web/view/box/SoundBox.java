package com.dictionary.web.view.box;

import com.dictionary.core.domain.MediaType;
import com.dictionary.core.domain.Size;
import com.dictionary.web.view.CustomTextField;
import com.dictionary.web.view.SelectTranscriptionVariant;
import com.dictionary.web.view.button.AudioButton;
import com.dictionary.web.view.button.CustomButton;
import com.dictionary.web.view.button.FileButton;
import com.dictionary.web.view.layout.FullHorizontalLayout;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.SelectVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class SoundBox extends VerticalLayout {

    private final List<Sound> sounds;


    public SoundBox() {
        setMargin(false);
        setPadding(false);
        sounds = new ArrayList<>();
        addSound();
    }

    public void addSound() {
        var variant = new SelectTranscriptionVariant();
        variant.addThemeVariants(SelectVariant.LUMO_SMALL);
        variant.setWidth(Size.PX_75);
        var transcription = CustomTextField.builder().value("")
                .fullWidth().clearButton(true).build();
        transcription.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        var buttonClose = CustomButton.builder().icon(new Icon("lumo", "cross")).build();
        buttonClose.addThemeVariants(ButtonVariant.LUMO_SMALL);
        var audioButton = new AudioButton();
        audioButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        var fileButton = new FileButton(MediaType.getExtensionsFromMediaTypeList(MediaType.getAudioTypes()));
        fileButton.setFileReceiver(audioButton::setFile);
        fileButton.addThemeVariants(ButtonVariant.LUMO_SMALL);

        var hl = new FullHorizontalLayout();

        hl.add(variant, transcription, audioButton, fileButton, buttonClose);
        sounds.add(new Sound(variant, transcription));
        buttonClose.addClickListener(
                listener -> {
                    if (this.getComponentCount() > 1) {
                        this.remove(hl);
                    } else {
                        Notification.show("You can't remove a single example");
                    }
                });
        add(hl);
    }

    @AllArgsConstructor
    @Getter
    public static class Sound {
        private final SelectTranscriptionVariant variant;
        private final TextField transcription;
    }

    public List<Sound> getSounds() {
        return sounds;
    }
}
