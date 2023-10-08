package com.dictionary.web.view;

import com.dictionary.core.domain.AudioFile;
import com.dictionary.core.domain.PictureFile;
import com.dictionary.core.domain.Size;
import com.dictionary.core.domain.Transcription;
import com.dictionary.web.view.button.AudioButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;
import java.util.Objects;


public class CardReader extends VerticalLayout {

    {
        setWidthFull();
        setAlignItems(Alignment.CENTER);
        setSpacing(false);
        setPadding(false);
        setMargin(false);
    }

    public CardReader(String expression, PictureFile pictureFile, String explanation, List<Transcription> transcriptions, List<String> examples) {
        var header = createHeader();
        var caption = createCaption(expression);
        header.add(caption);
        createTranscriptions(header, transcriptions);
        add(header);
        add(createPicture(pictureFile));
        add(createExplanation(explanation));
        add(createExamples(examples));
    }

    private static Span createCaption(String expression) {
        var caption = new Span(expression);
        caption.setWidthFull();
        return caption;
    }

    private static void createTranscriptions(HorizontalLayout header, List<Transcription> transcriptions) {
        for (var transcription : transcriptions) {
            if (Objects.nonNull(transcription.getFile())) {
                var audioButton = new AudioButton((AudioFile) transcription.getFile());
                audioButton.setTooltipText(transcription.getValue());
                header.add(audioButton);
            }
        }
    }

    private static HorizontalLayout createHeader() {
        var header = new HorizontalLayout();
        header.setWidthFull();
        header.getStyle().set("border-top-left-radius", "10px");
        header.getStyle().set("border-top-right-radius", "10px");
        header.getStyle().set("border", "3px solid #504F51");
        header.getStyle().set("padding", "15px");
        header.setVerticalComponentAlignment(Alignment.CENTER);
        header.setAlignItems(Alignment.CENTER);
        return header;
    }

    private static VerticalLayout createPicture(PictureFile pictureFile) {
        Picture picture = Objects.nonNull(pictureFile) ? new Picture(pictureFile) : new StandardDefaultPicture();
        picture.setWidthFull();
        picture.setMaxHeight(Size.PX_400);
        var pictureLayout = new VerticalLayout();
        pictureLayout.setSpacing(false);
        pictureLayout.setMargin(false);
        pictureLayout.setPadding(false);
        pictureLayout.getStyle().set("border-left", "3px solid #504F51");
        pictureLayout.getStyle().set("border-right", "3px solid #504F51");
        pictureLayout.getStyle().set("border-bottom", "3px solid #504F51");
        pictureLayout.add(picture);
        return pictureLayout;
    }

    private static HorizontalLayout createExplanation(String explanation) {
        var explanationSpan = new Span(explanation);
        explanationSpan.getStyle().set("font-weight", "bold");
        explanationSpan.getStyle().set("text-align", "center");
        explanationSpan.setWidthFull();
        var explanationLayout = new HorizontalLayout(explanationSpan);
        explanationLayout.getStyle().set("border-left", "3px solid #504F51");
        explanationLayout.getStyle().set("border-right", "3px solid #504F51");
        explanationLayout.setAlignItems(Alignment.CENTER);
        explanationLayout.setWidthFull();
        return explanationLayout;
    }

    private static VerticalLayout createExamples(List<String> examples) {
        var exampleLayout = new VerticalLayout();
        exampleLayout.getStyle().set("border-left", "3px solid #504F51");
        exampleLayout.getStyle().set("border-right", "3px solid #504F51");
        exampleLayout.getStyle().set("border-bottom", "3px solid #504F51");
        exampleLayout.getStyle().set("border-bottom-left-radius", "10px");
        exampleLayout.getStyle().set("border-bottom-right-radius", "10px");
        exampleLayout.setAlignItems(Alignment.START);
        exampleLayout.setWidthFull();

        for (var example : examples) {
            var span = new Span(example);
            span.getStyle().set("font-style", "italic");
            span.getStyle().set("text-align", "center");
            span.setWidthFull();
            var spanLayout = new HorizontalLayout(span);
            spanLayout.setWidthFull();
            spanLayout.getStyle().set("border", "3px solid #504F51");
            spanLayout.getStyle().set("border-radius", "10px");
            spanLayout.setAlignItems(Alignment.CENTER);
            exampleLayout.add(spanLayout);
        }
        return exampleLayout;
    }
}
