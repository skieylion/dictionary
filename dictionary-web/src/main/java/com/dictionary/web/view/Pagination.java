package com.dictionary.web.view;

import com.dictionary.web.service.Slider;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import lombok.Getter;

import java.util.function.Consumer;


public class Pagination extends HorizontalLayout {

    @Getter
    private final Button prev;
    @Getter
    private final Button next;

    private final ProgressBar progressBar;
    private Consumer<Integer> consumer;

    public Pagination() {
        setWidthFull();
        setDefaultVerticalComponentAlignment(Alignment.CENTER);
        prev = new Button();
        prev.setIcon(new Icon(VaadinIcon.ARROW_LEFT));
        progressBar = new ProgressBar();
        progressBar.setMin(0);
        progressBar.setValue(1);
        progressBar.setWidthFull();
        next = new Button();
        next.setIcon(new Icon(VaadinIcon.ARROW_RIGHT));
        add(prev, progressBar, next);
    }

    public void setSize(int size) {
        progressBar.setMax(size);
        Slider slider = new Slider(size);
        next.addClickListener(l -> slider.next());
        prev.addClickListener(l -> slider.prev());
        slider.change((direction, index) -> {
            prev.setEnabled(true);
            next.setEnabled(true);
            if (index == 0) {
                prev.setEnabled(false);
            }
            if (index == size - 1) {
                next.setEnabled(false);
            }
            progressBar.setValue(index + 1);
            if (consumer != null) {
                consumer.accept(index);
            }
        });
        slider.init();
    }

    public void setConsumer(Consumer<Integer> consumer) {
        this.consumer = consumer;
    }
}
