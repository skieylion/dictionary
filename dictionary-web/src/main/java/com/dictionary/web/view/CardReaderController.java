package com.dictionary.web.view;

import com.dictionary.web.view.button.CustomButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CardReaderController extends VerticalLayout {
    {
        setWidthFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("border-radius", "10px");
        getStyle().set("padding", "10px");
        getStyle().set("border", "3px solid #504F51");
    }

    private final Button next;
    private final Button prev;
    private final Button study;

    public CardReaderController() {
        var layout = new HorizontalLayout();
        next = CustomButton.builder().icon(VaadinIcon.ARROW_CIRCLE_RIGHT).build();
        prev = CustomButton.builder().icon(VaadinIcon.ARROW_CIRCLE_LEFT).build();
        study = CustomButton.builder().icon(VaadinIcon.ACADEMY_CAP).build();
        setStudyVisible(false);
        layout.add(prev, next, study);
        add(layout);
    }

    public void next(Runnable runnable) {
        next.addClickListener(listener -> runnable.run());
    }

    public void prev(Runnable runnable) {
        prev.addClickListener(listener -> runnable.run());
    }

    public void setStudyVisible(boolean state) {
        study.setEnabled(state);
    }

    public void close(Runnable runnable) {
        study.addClickListener(listener -> runnable.run());
    }
}
