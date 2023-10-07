package com.dictionary.application.domain.dto;

import com.dictionary.application.domain.MediaFile;
import com.dictionary.application.domain.TranscriptionVariant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TranscriptionDTO {
    private String value;
    private TranscriptionVariant variant;
    private String fileId;
    private String fileName;
    private MediaFile file;
}
