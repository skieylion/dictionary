package com.example.application.view;

import com.example.application.domain.TranscriptionVariant;
import com.vaadin.flow.component.select.Select;

public class SelectTranscriptionVariant extends Select<TranscriptionVariant> {
    public SelectTranscriptionVariant() {
        setItems(TranscriptionVariant.US, TranscriptionVariant.UK);
        setValue(TranscriptionVariant.US);
    }

    public TranscriptionVariant getTranscriptionVariant() {
        return getValue();
    }
}
