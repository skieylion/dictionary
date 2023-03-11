package com.example.application.view;

import com.example.application.domain.File;
import com.example.application.domain.Size;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public final class FileUploader extends Upload {
    private final File file = new File();
    private boolean isExist = false;

    private FileUploader(MemoryBuffer buffer) {
        super(buffer);
        Button button = new Button(new Icon(VaadinIcon.FILE_ADD));
        button.addThemeVariants(ButtonVariant.LUMO_ICON);
        setUploadButton(button);
        setWidth(Size.PERCENT_100);
        file.setBuffer(buffer);

        addSucceededListener(event -> {
            file.setFileName(event.getFileName());
            file.setSize(event.getContentLength());
            file.setMimeType(event.getMIMEType());
            isExist = true;
        });
    }

    public Optional<File> getFile() {
        return isExist ? Optional.of(file) : Optional.empty();
    }

    public static byte[] getInputStreamFromFile(File file) {
        try (InputStream is = file.getBuffer().getInputStream()) {
            return is.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load input stream from the file", e);
        }
    }

    public static String getBase64FromFile(File file) {
        return new String(getInputStreamFromFile(file), StandardCharsets.UTF_8);
    }

    public static FileUploader createInstance() {
        return new FileUploader(new MemoryBuffer());
    }
}
