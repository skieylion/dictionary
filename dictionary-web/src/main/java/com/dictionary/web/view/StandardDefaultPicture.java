package com.dictionary.web.view;

import com.dictionary.core.domain.MediaFile;
import com.dictionary.core.domain.PictureFile;

public class StandardDefaultPicture extends Picture {
    private static final String NAME = "image-default.png";

    public StandardDefaultPicture() {
        super(new PictureFile(MediaFile.getMediaFileFromResourceByName(NAME)));
    }
}
