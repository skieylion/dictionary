package com.dictionary.core.domain;

import com.dictionary.core.exception.ExtensionNotSupportedException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

public enum MediaType {
    JPE("jpe", "image/jpeg"),
    JPEG("jpeg", "image/jpeg"),
    JPG("jpg", "image/jpeg"),
    PNG("png", "image/png"),
    MP3("mp3", "audio/mpeg"),
    MID("mid", "audio/midi"),
    MIDI("wav", "audio/wav"),
    OGG("ogg", "audio/ogg");

    @Getter
    private final String extension;
    @Getter
    private final String mimeType;

    MediaType(String extension, String mimeType) {
        this.extension = extension;
        this.mimeType = mimeType;
    }

    public static Map<String, String> getMediaTypes() {
        return Arrays.stream(MediaType.values())
                .flatMap(mt -> Map.of(mt.extension, mt.mimeType).entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static MediaType getMediaTypeByExtension(String extension) {
        for (var mType : MediaType.values()) {
            if (mType.getExtension().equalsIgnoreCase(extension)) {
                return mType;
            }
        }
        throw new ExtensionNotSupportedException("The extension [" + extension + "] is not supported");
    }

    public static List<MediaType> getAudioTypes() {
        return Arrays.stream(MediaType.values())
                .filter(mediaType -> mediaType.mimeType.contains("audio/"))
                .collect(Collectors.toList());
    }

    public static List<MediaType> getImageTypes() {
        return Arrays.stream(MediaType.values())
                .filter(mediaType -> mediaType.mimeType.contains("image/"))
                .collect(Collectors.toList());
    }

    public static String getExtensionsFromMediaTypeList(List<MediaType> mediaTypeList) {
        return mediaTypeList.stream()
                .map(mediaType -> "." + mediaType.extension)
                .collect(Collectors.joining(","));
    }
}
