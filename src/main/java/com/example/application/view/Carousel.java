package com.example.application.view;

import com.example.application.domain.Size;
import com.example.application.service.Slider;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.StreamResource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class Carousel extends FullVerticalLayout {
    private static final String PASTE_JS;
    private static final String DEFAULT_IMAGE_NAME = "image-default.png";

    static {
        try (InputStream is = Carousel.class.getResourceAsStream("/paste.js")) {
            PASTE_JS = new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("paste.js is not loaded", e);
        }
    }


    private final Image image;



    public Carousel(String height) {

        image = Picture.createInstance(DEFAULT_IMAGE_NAME).height(height).dashed();
        add(image);

        TextField clipField = FullTextField.createInstance().placeholder("paste an image (ctrl+c / ctrl+v) ...");
        getElement().executeJs(PASTE_JS, clipField.getElement(), this);

        HorizontalLayout layout = FullHorizontalLayout.createInstance();
        layout.add(clipField);

        add(layout);
    }

    @ClientCallable()
    private void upload(String dataUrl) {
        if (dataUrl.startsWith("data:")) {
            byte[] imgBytes = Base64.getDecoder().decode(dataUrl.substring(dataUrl.indexOf(',') + 1));
            image.setSrc(new StreamResource("image.png", () -> new ByteArrayInputStream(imgBytes)));
        }
    }

}

/*
*     private void createSlider(Button prev, Button next, int count) {
        Slider slider = new Slider(count)
                .change((Slider.State state) -> {
                    prev.setEnabled(true);
                    next.setEnabled(true);
                    if (state == Slider.State.START) {
                        prev.setEnabled(false);
                    } else if (state == Slider.State.FINISH) {
                        next.setEnabled(false);
                    } else if (state == Slider.State.ONE) {
                        prev.setEnabled(false);
                        next.setEnabled(false);

                    }
                });
        slider.current(0);
        prev.addClickListener(event -> slider.prev());
        next.addClickListener(event -> slider.next());
    }
* */