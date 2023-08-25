package com.dictionary.application.service.mapper;

import com.dictionary.application.domain.AudioItem;
import com.dictionary.application.domain.Transcription;
import com.dictionary.application.view.*;
import com.dictionary.application.view.button.CustomButton;
import com.dictionary.application.view.layout.FullHorizontalLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Set;

public class TranscriptionMapper {
    public Set<AudioItem> convertTranscriptionsToAudioItems(Set<Transcription> transcriptions) {

        return null;
    }

    public AudioItem convertTranscriptionToAudioItem(Transcription transcription) {
        SelectTranscriptionVariant transcriptionVariant = new SelectTranscriptionVariant();
        transcriptionVariant.setValue(transcription.getVariant());
        TextField textField = CustomTextField.builder()
                .fullWidth()
                .value(transcription.getValue())
                .clearButton(true)
                .placeholder("transcription ...")
                .build();

        AudioItem.builder()
                .select(transcriptionVariant)
                .layout(new FullHorizontalLayout())
                .textField(textField)
                .closeButton(CustomButton.builder().icon(VaadinIcon.CLOSE).build())
                .plusButton(CustomButton.builder().icon(VaadinIcon.PLUS).build())
                .buttonUpload(FileUploader.createInstance())
                .build();
        return null;
    }

}
