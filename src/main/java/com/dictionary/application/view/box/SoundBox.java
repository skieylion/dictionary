package com.dictionary.application.view.box;

import com.dictionary.application.domain.AudioFile;
import com.dictionary.application.domain.MediaFile;
import com.dictionary.application.domain.MediaType;
import com.dictionary.application.domain.Size;
import com.dictionary.application.view.CustomTextField;
import com.dictionary.application.view.button.AudioButton;
import com.dictionary.application.view.button.FileButton;
import com.dictionary.application.view.layout.FullHorizontalLayout;
import com.dictionary.application.view.SelectTranscriptionVariant;
import com.dictionary.application.view.button.CustomButton;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.SelectVariant;
import com.vaadin.flow.component.textfield.TextFieldVariant;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SoundBox extends VerticalLayout {
    public SoundBox() {
        setMargin(false);
        setPadding(false);
        addSound();
    }

    public void addSound() {
        var variant = new SelectTranscriptionVariant();
        variant.addThemeVariants(SelectVariant.LUMO_SMALL);
        variant.setWidth(Size.PX_75);
        var textField = CustomTextField.builder().value("").fullWidth()
                .clearButton(true).build();
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        var buttonClose = CustomButton.builder()
                .icon(new Icon("lumo", "cross")).build();
        buttonClose.addThemeVariants(ButtonVariant.LUMO_SMALL);
        var audioButton = new AudioButton();
        audioButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        var fileButton = new FileButton(MediaType.getExtensionsFromMediaTypeList(MediaType.getAudioTypes()));
        fileButton.setFileReceiver(audioButton::setFile);
        fileButton.addThemeVariants(ButtonVariant.LUMO_SMALL);

        var hl = new FullHorizontalLayout();

        hl.add(variant, textField, audioButton, fileButton, buttonClose);
        buttonClose.addClickListener(listener -> {
            if (this.getComponentCount() > 1) {
                this.remove(hl);
            } else {
                Notification.show("You can't remove a single example");
            }
        });
        add(hl);
    }

    public List<MediaFile> getSounds() {
//        List<String> examples = new ArrayList<>();
//        int size = getComponentCount();
//        for (int i = 0; i < size; i++) {
//            var layout = (HorizontalLayout) getComponentAt(i);
//            var textField = (TextField) layout.getComponentAt(0);
//            examples.add(textField.getValue());
//        }
        return null;
    }
}
