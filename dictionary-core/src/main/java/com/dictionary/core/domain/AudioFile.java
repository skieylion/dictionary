package com.dictionary.core.domain;

public class AudioFile extends MediaFile {

    public AudioFile(String name, String ext, byte[] data) {
        super(name, ext, data);
        if (!MediaType.getAudioTypes().contains(MediaType.getMediaTypeByExtension(ext))) {
            throw new IllegalArgumentException("The extension with value [" + ext + "] is not supported");
        }
    }

    public AudioFile(String name, byte[] data) {
        this(name.substring(0, name.indexOf(".")), name.substring(name.indexOf(".") + 1), data);
    }

    public AudioFile(MediaFile file) {
        this(file.getFullName(), file.getBytes());
    }


}
