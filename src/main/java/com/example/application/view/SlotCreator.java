package com.example.application.view;

import com.example.application.domain.Size;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Arrays;

public class SlotCreator {

    private final Div div = new SlotDiv();


    public SlotCreator() {
        FrontLayout front = new FrontLayout();
        front.buttonPlus.addClickListener(eventFront -> {
            BackLayout backLayout = new BackLayout();
            backLayout.closeButton.addClickListener(eventBack -> {
                div.removeAll();
                div.add(front.layout);
            });
            div.removeAll();
            div.add(backLayout.build());
        });

        div.add(front.layout);
    }

    public Div getComponent() {
        return div;
    }

    private static class FrontLayout {
        private final Button buttonPlus = CustomButton.builder().icon(VaadinIcon.PLUS).build();

        {
            buttonPlus.getStyle().set("position", "relative");
            buttonPlus.getStyle().set("top", "135px");
        }

        private final VerticalLayout layout = ComponentBuilder.builder(VerticalLayout.class)
                .emptyIndent(false, false)
                .width(Size.PERCENT_100)
                .add(buttonPlus)
                .align(FlexComponent.Alignment.CENTER)
                .build();

    }

    private static final class BackLayout {
        VerticalLayout layout = ComponentBuilder.builder(VerticalLayout.class)
                .emptyIndent(false, true)
                .width(Size.PERCENT_100)
                .align(FlexComponent.Alignment.CENTER)
                .build();
        Select<String> select = new Select<>();

        TextField textField = new TextField();

        FileUploader uploader = FileUploader.createInstance();

        Button closeButton = CustomButton.builder().icon(VaadinIcon.CLOSE).build();

        HorizontalLayout horizontal = ComponentBuilder.builder(HorizontalLayout.class)
                .size(Size.PERCENT_100, Size.PERCENT_100)
                .add(CustomButton.builder().icon(VaadinIcon.PLUS).build())
                .add(closeButton)
                .build();

        {
            layout.setSpacing(false);
            select.setWidth(Size.PERCENT_100);
            textField.setPlaceholder("name ...");
            textField.setWidth(Size.PERCENT_100);
            uploader.setWidth(Size.PERCENT_100);
            horizontal.setVerticalComponentAlignment(FlexComponent.Alignment.END);

            //temporarily
            select.setItems(Arrays.asList("Cards", "Image", "Table"));
            select.setValue("Cards");

        }


        public VerticalLayout build() {
            layout.add(select);
            layout.add(textField);
            layout.add(uploader);
            layout.add(horizontal);
            return layout;
        }
    }
}
