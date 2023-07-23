package com.dictionary.application.view;

import com.dictionary.application.domain.PictureFile;
import com.dictionary.application.domain.Size;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;

public class CardMiniReader extends VerticalHeaderLayout {
    {
        setSpacing(false);
        setPadding(false);
        setMargin(false);
        setWidth(Size.PX_300);
        setHeight(Size.PX_300);
        getStyle().set("border-color", "#504F51");
        getHeader().getStyle().set("background-color", "#504F51");
        getLayout().setSpacing(false);
        getLayout().setMargin(false);
        getLayout().setPadding(false);
    }

    private static final int MAX_SYMBOLS = 30;

    private final Picture picture;
    private final Span text;

    @Getter
    private final Button buttonView;
    @Getter
    private final Button buttonPlay;
    @Getter
    private final Button buttonEdit;
    @Getter
    private final Button buttonRemove;


    public CardMiniReader(String caption) {
        super(caption);
        picture = new MiniDefaultPicture();
        picture.setWidthFull();
        picture.setHeight("190px");
        picture.getStyle().set("border-bottom", "3px solid #504F51");//3px solid #212A36
        HorizontalLayout buttonHorizontal = ComponentBuilder.builder(HorizontalLayout.class)
                .width(Size.PERCENT_100)
                .emptyIndent(false, false)
                .build();
        buttonHorizontal.getStyle().set("padding", "5px");
        buttonView = CustomButton.builder().icon(VaadinIcon.EYE).build();
        buttonPlay = CustomButton.builder().icon(VaadinIcon.PLAY).build();
        buttonEdit = CustomButton.builder().icon(VaadinIcon.EDIT).build();
        buttonRemove = CustomButton.builder().icon(VaadinIcon.CLOSE).build();
        text = new Span("");
        text.getStyle().set("position", "relative");
        text.getStyle().set("left", "5px");

        buttonHorizontal.add(buttonView, buttonPlay, buttonEdit, buttonRemove);
        add(picture);
        add(text);
        add(buttonHorizontal);
    }

    public void setText(String text) {
        this.text.setText(text.length() > MAX_SYMBOLS ? text.substring(0, MAX_SYMBOLS) + "..." : text);
        this.text.setTitle(text);
    }

    public void setPictureFile(PictureFile pictureFile) {
        picture.setPictureFile(pictureFile);
    }
}
