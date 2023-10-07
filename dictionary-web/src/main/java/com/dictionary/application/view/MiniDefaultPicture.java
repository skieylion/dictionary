package com.dictionary.application.view;

import com.dictionary.application.domain.MediaFile;
import com.dictionary.application.domain.PictureFile;

public class MiniDefaultPicture extends Picture {

    private static final String NAME = "image-mini-default.png";

    public MiniDefaultPicture() {
        super(new PictureFile(MediaFile.getMediaFileFromResourceByName(NAME)));
    }
}
