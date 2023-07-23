package com.dictionary.application.view;

import com.dictionary.application.domain.Size;
import com.dictionary.application.domain.dto.SlotCreatorDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Arrays;
import java.util.function.Consumer;

public class SlotCreator {

    private final Div div = new SlotDiv();
    private Consumer<SlotCreatorDto> listener = slot -> {
    };

    public SlotCreator() {
        FrontLayout front = new FrontLayout();
        front.buttonPlus.addClickListener(eventFront -> {
            BackLayout backLayout = new BackLayout();
            backLayout.plusButton.addClickListener(event -> {
                SlotCreatorDto slotCreatorDto = new SlotCreatorDto();
                slotCreatorDto.setName(backLayout.textField.getValue());
                if (backLayout.uploader.getFile().isPresent()) {
                    slotCreatorDto.setFile(backLayout.uploader.getFile().get());
                }
                div.removeAll();
                div.add(front.layout);
                listener.accept(slotCreatorDto);
            });
            backLayout.closeButton.addClickListener(eventBack -> {
                div.removeAll();
                div.add(front.layout);
            });
            div.removeAll();
            div.add(backLayout.build());
        });

        div.add(front.layout);
    }

    public Div getWrapper() {
        return div;
    }

    public void addClickListener(Consumer<SlotCreatorDto> listener) {
        this.listener = listener;
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
        Button plusButton = CustomButton.builder().icon(VaadinIcon.PLUS).build();

        HorizontalLayout horizontal = ComponentBuilder.builder(HorizontalLayout.class)
                .size(Size.PERCENT_100, Size.PERCENT_100)
                .add(plusButton)
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
