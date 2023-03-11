package com.example.application.view;

import com.example.application.domain.Size;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SlotReader {

    private final Div div = new SlotDiv();
    private final Picture picture;
    private final Div spanDiv = new Div();
    private final Paragraph paragraph = new Paragraph("");
    private final HorizontalLayout spanHorizontal = ComponentBuilder.builder(HorizontalLayout.class)
            .width(Size.PERCENT_100)
            .emptyIndent(false, false)
            .build();
    private final VerticalLayout layout = ComponentBuilder.builder(VerticalLayout.class)
            .emptyIndent(false, false)
            .width(Size.PX_300)
            .align(FlexComponent.Alignment.CENTER)
            .build();
    private final HorizontalLayout buttonHorizontal = ComponentBuilder.builder(HorizontalLayout.class)
            .emptyIndent(false, false)
            .align(FlexComponent.Alignment.START)
            .build();
    private final HorizontalLayout horizontalButtonsAndSpan = ComponentBuilder.builder(HorizontalLayout.class)
            .emptyIndent(false, false)
            .width(Size.PX_300)
            .build();

    {
        spanDiv.getElement().getStyle().set("float", "right");
        spanDiv.setWidthFull();
        paragraph.getStyle().set("background-color", "blue");
        paragraph.getStyle().set("color", "white");
        paragraph.getElement().getStyle().set("float", "right");
        spanHorizontal.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        spanHorizontal.getStyle().set("position", "relative");
        spanHorizontal.getStyle().set("left", "-10px");
        buttonHorizontal.getStyle().set("position", "relative");
        buttonHorizontal.getStyle().set("left", "10px");
        horizontalButtonsAndSpan.getStyle().set("position", "relative");
        horizontalButtonsAndSpan.setHeight("0px");
        horizontalButtonsAndSpan.getStyle().set("top", "-70px");
    }


    public SlotReader(String imageName, String stat) {
        picture = Picture
                .createInstance(imageName)
                .height(Size.PX_300)
                .width(Size.PX_300);
        paragraph.setText(stat);
        spanDiv.add(paragraph);
        spanHorizontal.add(spanDiv);
        buttonHorizontal.add(CustomButton.builder().icon(VaadinIcon.PLUS).build());
        buttonHorizontal.add(CustomButton.builder().icon(VaadinIcon.EDIT).build());
        buttonHorizontal.add(CustomButton.builder().icon(VaadinIcon.EYE).build());
        horizontalButtonsAndSpan.add(buttonHorizontal, spanHorizontal);
        layout.add(picture);
        layout.add(horizontalButtonsAndSpan);
        div.add(layout);
    }

    public Div getComponent() {
        return div;
    }

}
