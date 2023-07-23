package com.dictionary.application.domain;

public class PictureFile extends MediaFile {

    public PictureFile(String name, String ext, byte[] data) {
        super(name, ext, data);
        if (!MediaType.getImageTypes().contains(MediaType.getMediaTypeByExtension(ext))) {
            throw new IllegalArgumentException("The extension with value [" + ext + "] is not supported");
        }
    }

    public PictureFile(String name, byte[] data) {
        this(name.substring(0, name.indexOf(".")), name.substring(name.indexOf(".") + 1), data);
    }

    public PictureFile(MediaFile file) {
        this(file.getFullName(), file.getBytes());
    }
}
