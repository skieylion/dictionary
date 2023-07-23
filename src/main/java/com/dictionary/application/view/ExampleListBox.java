package com.dictionary.application.view;

import com.dictionary.application.domain.ExampleItem;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.util.List;
import java.util.stream.Collectors;

public class ExampleListBox extends ListBox {

    public ExampleListBox() {
        super(4);
        createExample("");
    }

    private void addExampleItem(ExampleItem exampleItem) {
        exampleItem.getLayout().add(exampleItem.getTextField());
        exampleItem.getLayout().add(exampleItem.getCloseButton());
        exampleItem.getLayout().add(exampleItem.getPlusButton());
    }

    private ExampleItem createExampleItem(String text) {
        var layout = new FullHorizontalLayout();
        //layout.getStyle().set("border-radius", "10px");
        //layout.getStyle().set("padding", "15px");
        //layout.getStyle().set("border", "3px solid #504F51");

        return ExampleItem.builder()
                .layout(layout)
                .textField(CustomTextField.builder()
                        .value(text)
                        .fullWidth()
                        .clearButton(true)
                        .placeholder("an example ...")
                        .build())
                .closeButton(CustomButton.builder().icon(VaadinIcon.CLOSE).build())
                .plusButton(CustomButton.builder().icon(VaadinIcon.PLUS).build())
                .build();
    }

    private void createExample(String text) {
        ExampleItem exampleItem = createExampleItem(text);
        addExampleItem(exampleItem);
        clickRemoveListener(exampleItem);
        clickAddListener(exampleItem, () -> createExample(text));
        addItem(exampleItem);
        add(exampleItem.getLayout());
        refreshState();
    }

    public List<ExampleItem> getExamples() {
        return getItems().stream()
                .map(fragmentLayout -> (ExampleItem) fragmentLayout)
                .collect(Collectors.toList());
    }

    public void setExamples(List<String> examples) {
        clearItems();
        examples.forEach(this::createExample);
    }
}