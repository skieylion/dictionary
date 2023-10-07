package com.dictionary.web.view.box;

import com.dictionary.web.domain.*;
import com.dictionary.web.domain.dto.TranscriptionDTO;
import com.dictionary.web.view.CustomTextField;
import com.dictionary.web.view.FileUploader;
import com.dictionary.web.view.layout.FullHorizontalLayout;
import com.dictionary.web.view.layout.FullVerticalLayout;
import com.dictionary.web.view.SelectTranscriptionVariant;
import com.dictionary.web.view.button.AudioButton;
import com.dictionary.web.view.button.CustomButton;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class AudioListBox extends ListBox {

    public AudioListBox() {
        super(4);
        createAudio(createAudioItem());
    }

    private void addAudioItem(AudioItem audioItem) {
        audioItem.getSelect().setWidth(Size.PX_75);
        VerticalLayout verticalLayout = new FullVerticalLayout();
        HorizontalLayout horizontalLayout = new FullHorizontalLayout();
        horizontalLayout.add(audioItem.getAudioPlayButton());
        horizontalLayout.add(audioItem.getAudioRemoveButton());
        horizontalLayout.add(audioItem.getSelect());
        horizontalLayout.add(audioItem.getTextField());
        horizontalLayout.add(audioItem.getCloseButton());
        horizontalLayout.add(audioItem.getPlusButton());
        verticalLayout.add(horizontalLayout);
        verticalLayout.add(audioItem.getButtonUpload());
        audioItem.getLayout().add(verticalLayout);
    }

    private AudioItem createAudioItem() {
        var layout = new FullHorizontalLayout();
        layout.getStyle().set("border-radius", "10px");
        layout.getStyle().set("padding", "15px");
        layout.getStyle().set("border", "3px solid #504F51");
        return addPlayer(AudioItem.builder()
                .audioPlayButton((AudioButton) CustomButton.builder(new AudioButton())
                        .build())
                .audioRemoveButton(CustomButton.builder()
                        .icon(VaadinIcon.CLOSE_SMALL)
                        .enabled(false)
                        .build())
                .select(new SelectTranscriptionVariant())
                .layout(layout)
                .textField(CustomTextField.builder().fullWidth().clearButton(true).placeholder("transcription ...").build())
                .closeButton(CustomButton.builder().icon(VaadinIcon.CLOSE).build())
                .plusButton(CustomButton.builder().icon(VaadinIcon.PLUS).build())
                .buttonUpload(FileUploader.createInstance())
                .build());
    }

    private AudioItem addPlayer(AudioItem audioItem) {
        audioItem.getButtonUpload().setAcceptedFileTypes(MediaType.getAudioTypes().stream()
                .map(MediaType::getExtension)
                .map(ext -> "." + ext)
                .collect(Collectors.joining(",")));
        audioItem.getButtonUpload().addFileUploaderListener(file -> {
            var dataSource = new String(file.getBytes(), StandardCharsets.UTF_8);
            System.out.println(dataSource.substring(0, 50));
            audioItem.getAudioPlayButton().setFile(file);
            audioItem.getAudioRemoveButton().setEnabled(true);
        });
        audioItem.getAudioRemoveButton().addClickListener(event -> {
            audioItem.getAudioPlayButton().setEnabled(false);
            audioItem.getAudioRemoveButton().setEnabled(false);
            audioItem.getButtonUpload().clearFileList();
        });
        return audioItem;
    }

    private AudioItem createAudioItem(TranscriptionDTO transcription) {
        AudioItem audioItem = createAudioItem();
        audioItem.getSelect().setValue(transcription.getVariant());
        audioItem.getTextField().setValue(transcription.getValue());
        if (Objects.nonNull(transcription.getFile())) {
            audioItem.getAudioPlayButton().setFile(transcription.getFile());
            audioItem.getAudioRemoveButton().setEnabled(true);
        }
        return audioItem;
    }

    private void createAudio(AudioItem audioItem) {
        addAudioItem(audioItem);
        clickRemoveListener(audioItem);
        clickAddListener(audioItem, () -> createAudio(createAudioItem()));
        addItem(audioItem);
        add(audioItem.getLayout());
        refreshState();
    }

    public List<AudioItem> getAudioItems() {
        return getItems().stream()
                .map(f -> (AudioItem) f)
                .collect(Collectors.toList());
    }

    public void setTranscriptions(List<TranscriptionDTO> transcriptions) {
        clearItems();
        Optional.ofNullable(transcriptions)
                .orElse(new ArrayList<>()).stream()
                .map(this::createAudioItem)
                .forEach(this::createAudio);
    }
}
