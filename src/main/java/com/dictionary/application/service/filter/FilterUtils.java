package com.dictionary.application.service.filter;

import com.dictionary.application.domain.Example;
import com.dictionary.application.domain.Transcription;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class FilterUtils {
    private FilterUtils() {
    }

    public static Set<Example> examples(Set<Example> examples) {
        return examples.stream()
                .filter(example -> Objects.nonNull(example.getText()))
                .collect(Collectors.toSet());
    }

    public static Set<Transcription> transcriptions(Set<Transcription> transcriptions) {
        return transcriptions.stream()
                .filter(transcription -> Objects.nonNull(transcription.getFile()))
                .collect(Collectors.toSet());
    }
}
