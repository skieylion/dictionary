package com.dictionary.application.view;

import com.dictionary.application.domain.MediaType;
import com.dictionary.application.domain.PictureFile;
import com.dictionary.application.domain.Size;
import com.dictionary.application.view.layout.FullVerticalLayout;
import com.dictionary.application.view.layout.VerticalBorderLayout;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;
import com.vaadin.flow.component.textfield.TextField;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class PictureLoader extends VerticalLayout {

    private static final String DEFAULT_CLIP_TEXT = "paste an image (ctrl+c / ctrl+v) ...";
    private static final String PASTE_JS;

    private final Picture picture;
    private final VerticalBorderLayout layoutScroller;
    private final Pagination pagination;

    static {
        try (InputStream is = PictureLoader.class.getResourceAsStream("/paste.js")) {
            PASTE_JS = new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("paste.js is not loaded", e);
        }
    }

    public class Wrapper extends FullVerticalLayout {
        @ClientCallable()
        private void upload(String dataUrl) {
            if (dataUrl.startsWith("data:")) {
                String ext = dataUrl.substring(dataUrl.indexOf("/") + 1, dataUrl.indexOf(";"));
                byte[] imgBytes = Base64.getDecoder().decode(dataUrl.substring(dataUrl.indexOf(',') + 1));
                picture.setPictureFile(new PictureFile("paste_" + UUID.randomUUID(), ext, imgBytes));
            }
        }
    }

    public PictureLoader() {
        layoutScroller = new VerticalBorderLayout();
        picture = new StandardDefaultPicture();
        picture.setHeight(Size.PX_350);
        pagination = new Pagination();
        FileUploader uploader = createUploader(picture);
        Scroller scroller = createScroller(picture);
        TextField clipField = CustomTextField.builder().fullWidth().clearButton(true).placeholder(DEFAULT_CLIP_TEXT).build();
        var wrapper = createWrapper(clipField, scroller, uploader);
        add(wrapper);
    }

    public void setPictures(List<PictureFile> pictures) {
        pagination.setSize(pictures.size());
        pagination.setConsumer(index -> picture.setPictureFile(pictures.get(index)));
    }

    private static FileUploader createUploader(Picture picture) {
        FileUploader uploader = FileUploader.createInstance();
        uploader.setWidthFull();
        uploader.addFileUploaderListener(file -> {
            picture.setPictureFile(new PictureFile(file));
        });
        uploader.setAcceptedFileTypes(MediaType.getExtensionsFromMediaTypeList(MediaType.getImageTypes()));
        return uploader;
    }

    private VerticalLayout createWrapper(TextField clipField, Scroller scroller, FileUploader uploader) {
        var layout = new VerticalLayout();
        var wrapper = new Wrapper();
        wrapper.add(clipField);

        layout.removeAll();
        layoutScroller.removeAll();
        layoutScroller.add(scroller);
        layout.add(layoutScroller);
        TabSheet tabSheet = new TabSheet();
        tabSheet.add("find", pagination);
        tabSheet.add("paste", wrapper);
        tabSheet.add("upload", uploader);
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_BORDERED, TabSheetVariant.LUMO_TABS_CENTERED);
        tabSheet.setWidthFull();
        layout.add(tabSheet);
        wrapper.getElement().executeJs(PASTE_JS, clipField.getElement(), wrapper);
        return layout;
    }

    private static Scroller createScroller(Picture picture) {
        Scroller scroller = new Scroller();
        scroller.setScrollDirection(Scroller.ScrollDirection.BOTH);
        scroller.setWidthFull();
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.add(picture);
        scroller.setContent(verticalLayout);
        return scroller;
    }

    public void setPicture(PictureFile file) {
        picture.setPictureFile(file);
    }

    @Deprecated
    public PictureFile getPicture() {
        return picture.getPictureFile();
    }

    @Deprecated
    public boolean isStandardPicture() {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash1 = digest.digest(picture.getPictureFile().getBytes());
            byte[] hash2 = digest.digest(new StandardDefaultPicture().getPictureFile().getBytes());
            return Arrays.equals(hash1, hash2);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    public Optional<PictureFile> getPictureFile() {
        if (Objects.nonNull(picture) && !isStandardPicture()) {
            return Optional.of(picture.getPictureFile());
        }
        return Optional.empty();
    }

    public void setInvalid(boolean flag) {
        layoutScroller.getStyle().set("border-color", flag ? "red" : "#504F51");
    }
}
