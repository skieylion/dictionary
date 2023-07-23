package com.dictionary.application.view;

import com.dictionary.application.domain.MediaFile;
import com.dictionary.application.domain.PictureFile;

public class MountainPicture extends Picture {
    private static final String NAME = "mountain.png";

    public MountainPicture() {
        super(new PictureFile(MediaFile.getMediaFileFromResourceByName(NAME)));
    }
}
