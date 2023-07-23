package com.dictionary.application.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class EntryDto {

    @Data
    public static class TranscriptionDto {
        private String phoneticSpelling;
        private String audioFile;
        private String dialect;
    }

    private String lexemeId;
    private String text;
    private String lexicalCategoryId;
    private String lexicalCategoryText;
    private List<TranscriptionDto> transcriptionList;
    private String definition;
    private List<String> examples;
}
