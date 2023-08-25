package com.dictionary.application.view.button;

import com.dictionary.application.domain.MediaFile;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.util.Optional;
import java.util.UUID;

public class AudioButton extends Button {
    private final HtmlComponent audio = new HtmlComponent("audio");
    private final String id = UUID.randomUUID().toString();
    private final String js = "document.getElementById('" + id + "').play()";
    private MediaFile file;

    public AudioButton(MediaFile file) {
        this();
        setFile(file);
    }

    public AudioButton() {
        audio.setId(id);
        setIcon(new Icon(VaadinIcon.PLAY));
        addClickListener(event -> UI.getCurrent().getPage().executeJs(js));
        UI.getCurrent().add(audio);
        setEnabled(false);
    }

    public void setFile(MediaFile file) {
        this.file = file;
        AbstractStreamResource src = new StreamResource(file.getFullName(), () -> new ByteArrayInputStream(file.getBytes()));
        audio.getElement().setAttribute("src", src);
        setEnabled(true);
    }

    public Optional<MediaFile> getFile() {
        return file != null ? Optional.of(file) : Optional.empty();
    }
}
