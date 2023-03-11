package com.example.application.view;

import com.example.application.domain.ExampleLayout;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.util.List;
import java.util.stream.Collectors;

public class ExampleList extends LayoutList {

    private void addToLayout(ExampleLayout exampleLayout) {
        exampleLayout.getLayout().add(exampleLayout.getTextField());
        exampleLayout.getLayout().add(exampleLayout.getCloseButton());
        exampleLayout.getLayout().add(exampleLayout.getPlusButton());
    }

    private void createExample() {
        ExampleLayout exampleLayout = ExampleLayout.builder()
                .layout(new FullHorizontalLayout())
                .textField(FullTextField.createInstance().placeholder("an example ..."))
                .closeButton(CustomButton.builder().icon(VaadinIcon.CLOSE).build())
                .plusButton(CustomButton.builder().icon(VaadinIcon.PLUS).build())
                .build();
        addToLayout(exampleLayout);
        clickRemove(exampleLayout);
        clickAdd(exampleLayout, this::createExample);
        putLayout(exampleLayout);
        add(exampleLayout.getLayout());
        state();
    }

    public ExampleList() {
        createExample();
    }

    public List<String> getExamples() {
        return getFragmentList().stream()
                .map(fragmentLayout -> (ExampleLayout) fragmentLayout)
                .map(exampleLayout -> exampleLayout.getTextField().getValue())
                .collect(Collectors.toList());
    }
}