package com.dictionary.application.service.handler.context;


import com.dictionary.application.domain.CardContext;
import com.dictionary.application.domain.Size;
import com.dictionary.application.service.command.EditCardCommand;
import com.dictionary.application.view.ImageSearcher;
import com.dictionary.application.view.MenuBarWrapper;
import com.dictionary.application.view.MiniDefaultPicture;
import com.dictionary.application.view.Picture;
import com.dictionary.application.view.StandardDefaultPicture;
import com.dictionary.application.view.button.ButtonMini;
import com.vaadin.flow.component.icon.Icon;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

public class CardContextImageHandler extends CardContextBaseHandler {
    @Override
    CardContext context() {
        Picture picture = createPicture();
        ImageSearcher imageSearcher = createImageSearcher(picture);
        CardContext contextImage = CardContext.builder().title("image").component(picture).build();
        contextImage.setDefaultComponent(picture);
        contextImage.setButtons(List.of(ButtonMini.builder()
                .icon(new Icon("lumo", "edit"))
                .click(l -> {
                    contextImage.setComponent(imageSearcher);
                    contextImage.setButton(l.getSource());
                    new EditCardCommand(contextImage).execute();
                })
                .build()));
        contextImage.setDefaultButtons(contextImage.getButtons());
        return contextImage;
    }

    private Picture createPicture() {
        Picture picture = new StandardDefaultPicture();
        picture.setWidth(Size.PX_700);
        picture.setHeight(Size.PX_450);
        return picture;
    }

    private ImageSearcher createImageSearcher(Picture picture) {
        ImageSearcher imageSearcher = new ImageSearcher();
        imageSearcher.changeText(text -> {
            List<Picture> pictures = new ArrayList<>();
            IntStream.range(1, 10).limit(new Random().nextInt(10) + 4)
                    .forEach(i -> pictures.add(new MiniDefaultPicture()));
            imageSearcher.setPictures(pictures);
        });
        imageSearcher.setSelectConsumer(picture::setPictureFile);
        imageSearcher.setUnselectConsumer(pictureFile -> picture
                .setPictureFile(new StandardDefaultPicture().getPictureFile()));
        return imageSearcher;
    }
}
