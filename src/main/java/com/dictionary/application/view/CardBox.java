package com.dictionary.application.view;

import com.dictionary.application.domain.AudioItem;
import com.dictionary.application.domain.ElementType;
import com.dictionary.application.domain.Example;
import com.dictionary.application.domain.MediaFile;
import com.dictionary.application.domain.PictureFile;
import com.dictionary.application.domain.Size;
import com.dictionary.application.domain.Slot;
import com.dictionary.application.domain.Transcription;
import com.dictionary.application.domain.dto.CardDTO;
import com.dictionary.application.domain.dto.TranscriptionDTO;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.PendingJavaScriptResult;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.internal.Pair;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Data
public class CardBox extends VerticalLayout {
    {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setWidth(Size.PERCENT_100);
    }

    private final TextField expression;
    private final TextArea explanation;
    private final PictureLoader pictureLoader;
    private final ExampleListBox exampleListBox;
    private final AudioListBox audioListBox;
    private final TextArea translation;

    public CardBox() {
        expression = CustomTextField.builder().placeholder("expression...").fullWidth().build();
        explanation = CustomTextArea.builder().placeholder("explanation...").fullWidth().build();
        pictureLoader = new PictureLoader();
        exampleListBox = new ExampleListBox();
        audioListBox = new AudioListBox();
        translation = CustomTextArea.builder().placeholder("translation...").fullWidth().build();
        addIntoLayout();
    }

    public void setPictures(List<PictureFile> pictures) {
        pictureLoader.setPictures(pictures);
    }


    private void addIntoLayout() {
        add(expression);
        add(createVerticalHeaderLayout("image", pictureLoader));
        add(createVerticalHeaderLayout("examples", exampleListBox));
        add(createVerticalHeaderLayout("audio", audioListBox));
        add(createVerticalHeaderLayout("explanation", explanation));
        add(createVerticalHeaderLayout("translation", translation));
    }

    public CardDTO getCard() {
        CardDTO card = new CardDTO();
        card.setExpression(expression.getValue());
        card.setExplanation(explanation.getValue());
        card.setPictureFile(pictureLoader.getPicture());
        card.setTranslation(translation.getValue());
        card.setExamples(new ArrayList<>());
        for (var exampleItem : exampleListBox.getExamples()) {
            var value = exampleItem.getTextField().getValue();
            card.getExamples().add(value);
        }
        card.setTranscriptions(new ArrayList<>());
        for (var transcriptionItem : audioListBox.getAudioItems()) {
            var transcription = new TranscriptionDTO();
            transcription.setValue(transcriptionItem.getTextField().getValue());
            transcription.setVariant(transcriptionItem.getTranscriptionVariant());
            transcription.setFile(transcriptionItem.getFile().orElse(null));
            card.getTranscriptions().add(transcription);
        }
        return card;
    }

    public void setCard(CardDTO card) {
        expression.setValue(card.getExpression());
        explanation.setValue(card.getExplanation());
        translation.setValue(card.getTranslation());
        Optional.ofNullable(card.getPictureFile())
                .ifPresent(pictureLoader::setPicture);
        exampleListBox.setExamples(card.getExamples());
        audioListBox.setTranscriptions(card.getTranscriptions());
    }

    private static VerticalHeaderLayout createVerticalHeaderLayout(String caption, Component component) {
        var layout = new VerticalHeaderLayout(caption);
        layout.add(component);
        return layout;
    }
}
