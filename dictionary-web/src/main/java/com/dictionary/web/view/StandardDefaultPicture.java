package com.dictionary.web.view;

import com.dictionary.web.domain.MediaFile;
import com.dictionary.web.domain.PictureFile;

public class StandardDefaultPicture extends Picture {
    private static final String NAME = "image-default.png";

    public StandardDefaultPicture() {
        super(new PictureFile(MediaFile.getMediaFileFromResourceByName(NAME)));
    }
}
