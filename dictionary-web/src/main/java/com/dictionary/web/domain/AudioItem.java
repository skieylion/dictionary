package com.dictionary.web.domain;

import com.dictionary.core.domain.MediaFile;
import com.dictionary.core.domain.TranscriptionVariant;
import com.dictionary.web.view.button.AudioButton;
import com.dictionary.web.view.FileUploader;
import com.dictionary.web.view.SelectTranscriptionVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@SuperBuilder
@Getter
public class AudioItem extends ListBoxItem {
    private AudioButton audioPlayButton;
    private Button audioRemoveButton;
    private SelectTranscriptionVariant select;
    private TextField textField;
    private TranscriptionVariant transcriptionVariant;
    @Setter
    private FileUploader buttonUpload;

    public Optional<MediaFile> getFile() {
        return audioPlayButton.getFile();
    }
}
