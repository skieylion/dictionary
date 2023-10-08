package com.dictionary.web.view;

import com.dictionary.core.domain.MediaFile;
import com.dictionary.core.domain.PictureFile;

public class MountainPicture extends Picture {
    private static final String NAME = "mountain.png";

    public MountainPicture() {
        super(new PictureFile(MediaFile.getMediaFileFromResourceByName(NAME)));
    }
}
