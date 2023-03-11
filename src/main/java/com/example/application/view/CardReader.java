package com.example.application.view;

import com.example.application.domain.Size;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class CardReader {
    private final VerticalLayout layout = new VerticalLayout();
    private final Paragraph expression = new Paragraph("");
    private Picture picture;
    private final Paragraph explanation = new Paragraph("");
    private final VerticalLayout layoutExamples = new VerticalLayout();
    private final VerticalLayout layoutPicture = ComponentBuilder.builder(VerticalLayout.class)
            .emptyIndent(false, false)
            .size(Size.PERCENT_100, Size.PERCENT_100)
            .align(FlexComponent.Alignment.CENTER)
            .build();
    private final HorizontalLayout audios = new HorizontalLayout();

    {
        layout.setWidth(Size.PERCENT_50);
        layoutExamples.setWidthFull();
        layoutExamples.getStyle().set("background-color", "#E5F0FF");
        layoutPicture.getStyle().set("border", "1px dashed gray");
    }

    public CardReader caption(String caption) {
        expression.setText(caption);//"Rainforest"
        return this;
    }

    public CardReader picture() {
        layoutPicture.removeAll();
        picture = Picture.createInstance("image-mini-default.png");
        layoutPicture.add(picture);
        return this;
    }

    public CardReader explanation(String description) {
        explanation.setText(description);//
        return this;
    }

    //"He worked tirelessly to improve safety conditions in the mines"
    public CardReader examples(List<String> examples) {
        layoutExamples.removeAll();
        examples.forEach(example -> layoutExamples.add(new Paragraph(example)));
        return this;
    }

    public CardReader audio(List<String> audioList) {//"wɜː(r)k (UK)"
        audios.removeAll();
        audioList.forEach(transcription -> {
            HorizontalLayout audioLayout = new HorizontalLayout();
            audioLayout.add(CustomButton.builder().icon(VaadinIcon.PLAY).build());
            audioLayout.add(new Paragraph(transcription));
            audios.add(audioLayout);
        });
        return this;
    }

    public static CardReader builder() {
        return new CardReader();
    }

    public VerticalLayout build() {
        layout.removeAll();
        layout.add(expression);
        layout.add(layoutPicture);
        layout.add(audios);
        layout.add(explanation);
        layout.add(layoutExamples);
        return layout;
    }


}
