package com.dictionary.core.domain;

public class DefaultAudioFile extends AudioFile {
    private static final String NAME = "default_audio.ogg";
    private static final MediaFile FILE = MediaFile.getMediaFileFromResourceByName(NAME);

    public DefaultAudioFile() {
        super(FILE.getFullName(), FILE.getBytes());
    }
}
