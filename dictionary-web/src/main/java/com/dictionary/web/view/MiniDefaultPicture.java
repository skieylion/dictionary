package com.dictionary.web.view;

import com.dictionary.core.domain.MediaFile;
import com.dictionary.core.domain.PictureFile;

public class MiniDefaultPicture extends Picture {

    private static final String NAME = "image-mini-default.png";

    public MiniDefaultPicture() {
        super(new PictureFile(MediaFile.getMediaFileFromResourceByName(NAME)));
    }
}
