package com.dictionary.application.view.button;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.upload.Upload;
import elemental.json.JsonArray;

import java.util.UUID;

public class FileButton extends Button {
    private final HtmlComponent input = new HtmlComponent("input");
    private final String id = UUID.randomUUID().toString();
    private final String js = "document.getElementById('" + id + "').click()";

    //private final Upload uploader = new Upload();

    public FileButton() {
        input.setId(id);
        input.getElement().setAttribute("type", "file");
        input.getElement().getStyle().set("display", "none");
        setIcon(new Icon(VaadinIcon.PLAY));

        final String elementFiles = "files";






        addClickListener(event -> {
            UI.getCurrent().getPage().executeJs(js);
            System.out.println(input.getElement().getAttribute("files"));
            System.out.println(input.getElement().getAttribute("src"));
        });
        UI.getCurrent().add(input);
        //UI.getCurrent().add(uploader);
    }
}
