package com.example.application.domain;

import com.example.application.view.SelectTranscriptionVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class AudioLayout extends FragmentLayout {
    private SelectTranscriptionVariant select;
    private TextField textField;
    private TranscriptionVariant transcriptionVariant;
    private File file;
    private Upload buttonUpload;

}
