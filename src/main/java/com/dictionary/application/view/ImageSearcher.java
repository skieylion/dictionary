package com.dictionary.application.view;

import com.dictionary.application.domain.PictureFile;
import com.dictionary.application.domain.Size;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


public class ImageSearcher extends VerticalLayout {

    private final FlexLayout flexLayout;
    private Consumer<String> textConsumer;

    private List<Picture> pictures;
    private Consumer<PictureFile> selectConsumer;
    private Consumer<PictureFile> unselectConsumer;

    public ImageSearcher() {

        ProgressBar spinner = new ProgressBar();
        spinner.setIndeterminate(true);
        spinner.setVisible(false);
        spinner.setWidth(Size.PERCENT_50);

        flexLayout = new FlexLayout();
        flexLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        flexLayout.setAlignItems(FlexComponent.Alignment.CENTER);


        flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        Scroller scroller = createScroller(flexLayout);

        TextField textField = new TextField();
        textField.setWidth(Size.PERCENT_50);
        textField.addValueChangeListener(listener -> {
            Optional.ofNullable(textConsumer)
                    .ifPresent(textConsumer -> textConsumer
                            .accept(listener.getValue()));
        });

        var vl = new VerticalLayout(textField, spinner);
        vl.setSpacing(false);
        vl.setMargin(false);
        vl.setPadding(false);
        vl.setWidthFull();
        vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(vl);
        add(scroller);

        setWidthFull();
        setMargin(false);
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

    }

    private static Scroller createScroller(FlexLayout flexLayout) {
        Scroller scroller = new Scroller();
        scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
        scroller.setWidthFull();
        scroller.setContent(flexLayout);
        scroller.setHeight(Size.PX_300);
        return scroller;
    }

    private static Picture createPicture(Picture picture) {
        picture.getStyle().set("border-radius", "10px");
        picture.getStyle().set("width", "210px");
        picture.getStyle().set("height", "140px");
        picture.getStyle().set("padding", "5px");
        picture.addClassName("hoverable-border-image");
        return picture;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
        flexLayout.removeAll();
        pictures.forEach(picture -> {
            flexLayout.add(createPicture(picture));
            picture.addClickListener(listener -> {
                pictures.stream().filter(p -> p != picture).forEach(p -> p.removeClassName("hoverable-border-image-red"));
                pictures.forEach(p -> p.addClassName("hoverable-border-image"));
                if (picture.hasClassName("hoverable-border-image-red")) {
                    picture.removeClassName("hoverable-border-image-red");
                    Optional.ofNullable(unselectConsumer).ifPresent(consumer -> {
                        unselectConsumer.accept(picture.getPictureFile());
                    });
                } else {
                    picture.addClassName("hoverable-border-image-red");
                    Optional.ofNullable(selectConsumer).ifPresent(consumer -> {
                        consumer.accept(picture.getPictureFile());
                    });
                }
            });
        });
    }

    public void changeText(Consumer<String> textConsumer) {
        this.textConsumer = textConsumer;
    }

    public void setSelectConsumer(Consumer<PictureFile> selectConsumer) {
        this.selectConsumer = selectConsumer;
    }

    public void setUnselectConsumer(Consumer<PictureFile> unselectConsumer) {
        this.unselectConsumer = unselectConsumer;
    }
}
