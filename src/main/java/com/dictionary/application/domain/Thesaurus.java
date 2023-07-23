package com.dictionary.application.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Thesaurus {
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LexicalData {
        private String language;
        private String text;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class IdTextPair {
        private String id;
        private String text;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Example {
        private String text;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SubSense {
        private String id;
        private List<LexicalData> synonyms;
        private List<IdTextPair> registers;
        private List<IdTextPair> regions;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Sense {
        private String id;
        private List<LexicalData> antonyms;
        private List<Example> examples;
        private List<SubSense> subsenses;
        private List<LexicalData> synonyms;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Entry {
        private List<Sense> senses;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LexicalEntry {
        private List<Entry> entries;
        private String language;
        private IdTextPair lexicalCategory;
        private String text;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata {
        private String operation;
        private String provider;
        private String schema;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Result {
        private String id;
        private String language;
        private String type;
        private String word;
        private List<LexicalEntry> lexicalEntries;
    }

    private String id;

    private Metadata metadata;

    private List<Result> results;

    private String word;
}
