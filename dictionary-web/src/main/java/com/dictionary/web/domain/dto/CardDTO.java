package com.dictionary.web.domain.dto;

import com.dictionary.core.domain.PictureFile;
import lombok.Data;

import java.util.List;

@Data
public class CardDTO {
    private long id;
    private String expression;
    private String explanation;
    private String translation;
    private PictureFile pictureFile;
    private List<String> examples;
    private List<TranscriptionDTO> transcriptions;
}
