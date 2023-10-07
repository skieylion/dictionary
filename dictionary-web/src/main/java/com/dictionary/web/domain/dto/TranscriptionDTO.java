package com.dictionary.web.domain.dto;

import com.dictionary.web.domain.MediaFile;
import com.dictionary.web.domain.TranscriptionVariant;
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
