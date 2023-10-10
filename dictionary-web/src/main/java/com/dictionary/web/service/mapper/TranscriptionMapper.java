package com.dictionary.web.service.mapper;

import com.dictionary.web.domain.AudioItem;
import com.dictionary.core.domain.Transcription;
import com.dictionary.web.view.CustomTextField;
import com.dictionary.web.view.FileUploader;
import com.dictionary.web.view.SelectTranscriptionVariant;
import com.dictionary.web.view.button.CustomButton;
import com.dictionary.web.view.layout.FullHorizontalLayout;
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
