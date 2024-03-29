package com.dictionary.web.view;

import com.dictionary.core.domain.PictureFile;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;

public class Picture extends Image {
    private PictureFile file;

    public Picture(PictureFile file) {
        super(getStreamResource(file), "image");
        this.file = file;
    }

    public PictureFile getPictureFile() {
        return file;
    }

    public void setPictureFile(PictureFile file) {
        this.file = file;
        this.setSrc(getStreamResource(file));
    }

    private static StreamResource getStreamResource(PictureFile file) {
        return new StreamResource(file.getFullName(), () -> new ByteArrayInputStream(file.getBytes()));
    }
}
