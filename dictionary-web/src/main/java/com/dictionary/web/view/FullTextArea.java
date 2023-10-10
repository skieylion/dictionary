package com.dictionary.web.view;

import com.vaadin.flow.component.textfield.TextArea;

public final class FullTextArea extends TextArea {
    private FullTextArea() {

    }

    public static FullTextArea createInstance() {
        return new FullTextArea();
    }

    public FullTextArea placeholder() {
        return this;
    }
}
