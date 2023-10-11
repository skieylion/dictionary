package com.dictionary.web.view;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;

public class TextRecordField extends TextField {
    public TextRecordField() {
        super();
        var icon = new Icon(VaadinIcon.MICROPHONE);
        icon.getStyle().set("cursor", "pointer");

        setSuffixComponent(icon);

        icon.addClickListener(
                event -> {
                    icon.getUI()
                            .ifPresent(
                                    ui ->
                                            ui.getPage()
                                                    .executeJs(
                                                            "const recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition)();"
                                                                    + "recognition.onstart = function() { console.log('Voice recognition started. Try speaking into the microphone.'); };"
                                                                    + "recognition.onspeechend = function() { console.log('You stopped talking.'); recognition.stop(); };"
                                                                    + "recognition.onresult = function(event) { var transcript = event.results[0][0].transcript;  console.log(transcript); $0.$server.setText(transcript); };"
                                                                    + "recognition.start();",
                                                            getElement() // this binds 'this' ($0) to the current Java object
                                                    ));
                });
    }

    @ClientCallable
    public void setText(String text) {
        setValue(text.replace(".", "").toLowerCase());
    }
}
