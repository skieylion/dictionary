package com.dictionary.web.view;

import com.dictionary.core.domain.MediaFile;
import com.dictionary.core.domain.Size;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.function.Consumer;

public final class FileUploader extends Upload {

    private MediaFile file;

    private Consumer<MediaFile> fileUploaderListener = f -> {
    };

    private FileUploader(MemoryBuffer buffer) {
        super(buffer);
        Button button = new Button(new Icon(VaadinIcon.FILE_ADD));
        button.addThemeVariants(ButtonVariant.LUMO_ICON);
        setUploadButton(button);
        setWidth(Size.PERCENT_100);

        addSucceededListener(event -> {
            try (InputStream is = buffer.getInputStream()) {
                file = new MediaFile(event.getFileName(), is.readAllBytes());
                fileUploaderListener.accept(file);
            } catch (IOException e) {
                throw new RuntimeException("The file is not read", e);
            }
        });
    }

    public Optional<MediaFile> getFile() {
        return Optional.ofNullable(file);
    }

    public void addFileUploaderListener(Consumer<MediaFile> fileUploaderListener) {
        this.fileUploaderListener = fileUploaderListener;
    }

    public static FileUploader createInstance() {
        return new FileUploader(new MemoryBuffer());
    }
}
