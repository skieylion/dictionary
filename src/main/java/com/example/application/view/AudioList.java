package com.example.application.view;

import com.example.application.domain.AudioLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AudioList extends LayoutList {

    private void addToLayout(AudioLayout audioLayout) {
        VerticalLayout verticalLayout = new FullVerticalLayout();
        HorizontalLayout horizontalLayout = new FullHorizontalLayout();
        horizontalLayout.add(audioLayout.getSelect());
        horizontalLayout.add(audioLayout.getTextField());
        horizontalLayout.add(audioLayout.getCloseButton());
        horizontalLayout.add(audioLayout.getPlusButton());
        verticalLayout.add(audioLayout.getButtonUpload());
        verticalLayout.add(horizontalLayout);
        audioLayout.getLayout().add(verticalLayout);
    }

    private void createAudio() {
        AudioLayout audioLayout = AudioLayout.builder()
                .select(new SelectTranscriptionVariant())
                .layout(new FullHorizontalLayout())
                .textField(FullTextField.createInstance().placeholder("transcription ..."))
                .closeButton(CustomButton.builder().icon(VaadinIcon.CLOSE).build())
                .plusButton(CustomButton.builder().icon(VaadinIcon.PLUS).build())
                .buttonUpload(FileUploader.createInstance())
                .build();
        addToLayout(audioLayout);
        clickRemove(audioLayout);
        clickAdd(audioLayout, this::createAudio);
        putLayout(audioLayout);
        add(audioLayout.getLayout());
        state();
    }

    public AudioList() {
        createAudio();
    }
}
