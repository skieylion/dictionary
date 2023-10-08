package com.dictionary.web.view.slot;

import com.dictionary.core.domain.PictureFile;
import com.dictionary.core.domain.Size;
import com.dictionary.web.view.PictureLoader;
import com.dictionary.web.view.layout.VerticalHeaderLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

public class SlotEditorForm {

    private final VerticalLayout wrapper;
    private final TextField fieldName;

    @Getter
    private final PictureLoader pictureLoader;

    public SlotEditorForm() {
        wrapper = new VerticalLayout();
        fieldName = new TextField();
        fieldName.setPlaceholder("name ...");
        fieldName.setWidth(Size.PERCENT_100);
        pictureLoader = new PictureLoader();
        wrapper.setWidthFull();
        wrapper.setPadding(false);
        wrapper.setMargin(false);
    }

    public void setName(String name) {
        fieldName.setValue(name);
    }

    public String getName() {
        return fieldName.getValue();
    }

    public void setPicture(PictureFile file) {
        pictureLoader.setPicture(file);
    }

    @Deprecated
    public PictureFile getPicture() {
        return pictureLoader.getPicture();
    }

    public VerticalLayout getWrapper() {
        wrapper.removeAll();
        wrapper.add(fieldName);
        wrapper.add(new VerticalHeaderLayout("image", pictureLoader));

        return wrapper;
    }

    public static SlotEditorFormBuilder builder() {
        return new SlotEditorFormBuilder();
    }

    public static class SlotEditorFormBuilder {
        SlotEditorForm slotEditorForm = new SlotEditorForm();

        public SlotEditorFormBuilder name(String name) {
            slotEditorForm.setName(name);
            return this;
        }

        public SlotEditorFormBuilder picture(PictureFile pictureFile) {
            slotEditorForm.setPicture(pictureFile);
            return this;
        }

        public SlotEditorForm build() {
            return slotEditorForm;
        }
    }
}
