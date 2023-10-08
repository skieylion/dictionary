package com.dictionary.core.domain;

import lombok.Data;

import java.util.List;

@Data
public class UnsplashResponse {
    private String id;
    private List<Result> results;

    @Data
    public static class Result {
        private Link links;
        private URL urls;
    }

    @Data
    public static class Link {
        private String download;
    }
    @Data
    public static class URL {
        private String full;
    }


}
