package com.dictionary.web.view;

import com.dictionary.web.domain.MediaFile;
import com.dictionary.web.domain.PictureFile;

public class MountainPicture extends Picture {
    private static final String NAME = "mountain.png";

    public MountainPicture() {
        super(new PictureFile(MediaFile.getMediaFileFromResourceByName(NAME)));
    }
}
