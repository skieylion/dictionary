package com.dictionary.application.view;

import com.dictionary.application.domain.MediaFile;
import com.dictionary.application.domain.PictureFile;

public class StandardDefaultPicture extends Picture {
    private static final String NAME = "image-default.png";

    public StandardDefaultPicture() {
        super(new PictureFile(MediaFile.getMediaFileFromResourceByName(NAME)));
    }
}
