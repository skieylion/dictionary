package com.dictionary.web.view.button;

import com.dictionary.core.domain.MediaFile;
import com.dictionary.web.utils.MediaBase64ToMediaFileConverter;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class FileButton extends Button {
    private static final String LOAD_JS;

    static {
        try (InputStream is = FileButton.class.getResourceAsStream("/loading.js")) {
            LOAD_JS = new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("loading.js is not loaded", e);
        }
    }

    private final HtmlComponent fileInput = new HtmlComponent("input");
    private Consumer<MediaFile> mediaFileConsumer;

    public FileButton() {
        String fileId = UUID.randomUUID().toString();
        fileInput.setId(fileId);
        fileInput.getElement().setAttribute("type", "file");
        fileInput.getElement().getStyle().set("display", "none");
        String hiddenId = UUID.randomUUID().toString();
        HtmlComponent hiddenInput = new HtmlComponent("input");
        hiddenInput.setId(hiddenId);
        hiddenInput.getElement().setAttribute("type", "hidden");
        setIcon(new Icon(VaadinIcon.PAPERCLIP));
        var script = LOAD_JS.replaceAll("\\{fileId}", fileId)
                .replaceAll("\\{hiddenId}", hiddenId);
        addClickListener(event -> UI.getCurrent().getPage()
                .executeJs(script, this.getElement()));
        UI.getCurrent().add(fileInput, hiddenInput);
    }

    public FileButton(String accept) {
        this();
        fileInput.getElement().setAttribute("accept", accept);
    }

    @ClientCallable
    public void receiveFile(String dataSource) {
        Optional.ofNullable(mediaFileConsumer).ifPresent(consumer -> {
            var file = MediaBase64ToMediaFileConverter.convertToMediaFile(dataSource);
            consumer.accept(file);
        });
    }

    public void setFileReceiver(Consumer<MediaFile> consumer) {
        mediaFileConsumer = consumer;
    }
}
