package com.dictionary.web.service.handler.context;

import com.dictionary.core.domain.Size;
import com.dictionary.web.domain.CardContext;
import com.dictionary.web.domain.CardContextType;
import com.dictionary.web.domain.CardImageContext;
import com.dictionary.web.service.UnsplashService;
import com.dictionary.web.service.command.EditCardCommand;
import com.dictionary.web.view.ImageSearcher;
import com.dictionary.web.view.Picture;
import com.dictionary.web.view.StandardDefaultPicture;
import com.dictionary.web.view.button.ButtonMini;
import com.vaadin.flow.component.icon.Icon;

import java.util.List;
import java.util.stream.Collectors;

public class CardContextImageCreator extends CardContextBaseCreator {

    private final UnsplashService unsplashService;

    public CardContextImageCreator(UnsplashService unsplashService) {
        this.unsplashService = unsplashService;
    }

    @Override
    CardContext createContext() {
        Picture picture = createPicture();
        ImageSearcher imageSearcher = createImageSearcher(picture);
        CardImageContext contextImage =
                CardImageContext.builder().title("image").component(picture).picture(picture).build();
        contextImage.setDefaultComponent(picture);
        contextImage.setButtons(
                List.of(
                        ButtonMini.builder()
                                .icon(new Icon("lumo", "edit"))
                                .click(
                                        l -> {
                                            contextImage.setComponent(imageSearcher);
                                            contextImage.setButton(l.getSource());
                                            new EditCardCommand(contextImage).execute();
                                        })
                                .build()));
        contextImage.setDefaultButtons(contextImage.getButtons());
        contextImage.setContextType(CardContextType.IMAGE);
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
        imageSearcher.changeText(
                text ->
                        imageSearcher.setPictures(
                                unsplashService.findPictures(text).stream()
                                        .map(Picture::new)
                                        .collect(Collectors.toList())));
        imageSearcher.setSelectConsumer(picture::setPictureFile);
        imageSearcher.setUnselectConsumer(
                pictureFile -> picture.setPictureFile(new StandardDefaultPicture().getPictureFile()));
        return imageSearcher;
    }
}
