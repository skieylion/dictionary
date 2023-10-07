package com.dictionary.web.view.box;

import com.dictionary.web.domain.PictureFile;
import com.dictionary.web.domain.Size;
import com.dictionary.web.domain.dto.CardDTO;
import com.dictionary.web.domain.dto.TranscriptionDTO;
import com.dictionary.web.view.CustomTextArea;
import com.dictionary.web.view.CustomTextField;
import com.dictionary.web.view.PictureLoader;
import com.dictionary.web.view.layout.VerticalHeaderLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
