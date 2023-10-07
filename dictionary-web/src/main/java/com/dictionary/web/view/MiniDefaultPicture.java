package com.dictionary.web.view;

import com.dictionary.web.domain.MediaFile;
import com.dictionary.web.domain.PictureFile;

public class MiniDefaultPicture extends Picture {

    private static final String NAME = "image-mini-default.png";

    public MiniDefaultPicture() {
        super(new PictureFile(MediaFile.getMediaFileFromResourceByName(NAME)));
    }
}
