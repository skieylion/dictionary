package com.dictionary.web.domain;

import lombok.Data;

import java.util.List;

@Data
public class Search {

    @Data
    public static class Metadata {
        private byte limit;
        private byte offset;
        private String operation;
        private String provider;
        private String schema;
        private String sourceLanguage;
        private short total;
    }

    @Data
    public static class Result {
        private String id;
        private String label;
        private String matchString;
        private String matchType;
        private String region;
        private float score;
        private String word;
    }

    private Metadata metadata;

    private List<Result> results;

}
