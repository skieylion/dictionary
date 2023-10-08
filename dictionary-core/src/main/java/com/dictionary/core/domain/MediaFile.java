package com.dictionary.core.domain;

import com.dictionary.core.exception.ExtensionNotSupportedException;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class MediaFile {

    public static final Map<String, String> MIME_TYPES = MediaType.getMediaTypes();

    @Getter
    @Setter
    private String name;
    private final String ext;
    private final byte[] data;
    @Getter
    private final UUID uuid;

    public MediaType getMediaType() {
        try {
            return MediaType.getMediaTypeByExtension(ext);
        } catch (ExtensionNotSupportedException e) {
            throw new IllegalArgumentException("The extension [" + ext + "] is not supported");
        }
    }

    public String getMimeType() {
        return MIME_TYPES.get(ext);
    }

    public MediaFile(String name, String ext, byte[] data) {
        this.name = name;
        this.ext = ext;
        this.data = data;
        this.uuid = UUID.randomUUID();
        if (!MIME_TYPES.containsKey(ext)) {
            throw new IllegalArgumentException("The extension with value [" + ext + "] is not supported");
        }
    }

    public MediaFile(String name, byte[] data) {
        this(name.substring(0, name.indexOf(".")), name.substring(name.indexOf(".") + 1), data);
    }

    public byte[] getBytes() {
        return data;
    }

    public String getFullName() {
        return name + "." + ext;
    }

    public String getUuidAndExt() {
        return uuid.toString() + "." + ext;
    }

    public static MediaFile getMediaFileFromResourceByName(String name) {
        try (InputStream is = MediaFile.class.getClassLoader().getResourceAsStream(name)) {
            return new MediaFile(name, Objects.requireNonNull(is).readAllBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException("The file with name [" + name + "] is not found", e);
        }
    }
}
