package com.example.application.view;

import com.example.application.domain.Size;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CardMiniReader {

    private final Div div = new SlotDiv();

    private VerticalLayout verticalLayout = new VerticalLayout();
    private VerticalLayout verticalLayout2 = new VerticalLayout();
    private HorizontalLayout buttons = new HorizontalLayout();
    private Paragraph expression = new Paragraph("");
    private Paragraph example = new Paragraph("");


    {
        div.setHeight(Size.PX_350);
        verticalLayout.setMargin(false);
        verticalLayout.setPadding(false);
        verticalLayout.setSpacing(false);
        verticalLayout2.setMargin(false);
        verticalLayout2.setPadding(false);
        verticalLayout2.setSpacing(false);
        verticalLayout2.setWidth("280px");
        verticalLayout2.getStyle().set("position", "relative");
        verticalLayout2.getStyle().set("top", "-90px");
        verticalLayout2.getStyle().set("left", "10px");
        expression.getStyle().set("background-color", "red");
        expression.getStyle().set("color", "white");
        example.getStyle().set("background-color", "red");
        example.getStyle().set("color", "white");

    }

    public CardMiniReader(String expressionText, String exampleText) {
        expression.setText(expressionText);
        example.setText(exampleText);
        Picture picture = Picture.createInstance("image-mini-default.png")
                .height(Size.PX_300)
                .width(Size.PX_300);
        picture.getStyle().set("border-bottom", "1px dashed gray");
        verticalLayout.add(picture);
        verticalLayout2.add(expression);
        verticalLayout2.add(example);
        verticalLayout2.add(buttons);
        buttons.add(CustomButton.builder().icon(VaadinIcon.EYE).build());
        buttons.add(CustomButton.builder().icon(VaadinIcon.PLAY).build());
        buttons.add(CustomButton.builder().icon(VaadinIcon.EDIT).build());
        buttons.add(CustomButton.builder().icon(VaadinIcon.CLOSE).build());
        verticalLayout.add(verticalLayout2);
        div.add(verticalLayout);
    }

    public Div getComponent() {
        return div;
    }

}
