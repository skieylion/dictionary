package com.example.application.view;

import com.example.application.domain.Size;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;

public class Picture extends Image {
    private Picture(String name) {
        super(new StreamResource(name, () -> Picture.class
                .getClassLoader()
                .getResourceAsStream(name)), "image");
    }

    public static Picture createInstance(String name) {
        return new Picture(name);
    }

    public Picture height(String height) {
        setHeight(height);
        return this;
    }

    public Picture maxHeight(String height) {
        setMaxHeight(height);
        return this;
    }

    public Picture maxWidth(String width) {
        setMaxWidth(width);
        return this;
    }

    public Picture width(String width) {
        setWidth(width);
        return this;
    }

    public Picture dashed() {
        getStyle().set("border", "1px dashed gray");
        return this;
    }

}
