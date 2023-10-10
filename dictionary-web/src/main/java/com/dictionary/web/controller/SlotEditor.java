package com.dictionary.web.controller;

import com.dictionary.core.domain.FileProperty;
import com.dictionary.core.domain.PictureFile;
import com.dictionary.core.domain.Size;
import com.dictionary.core.domain.Slot;
import com.dictionary.web.service.FilePropertyService;
import com.dictionary.web.service.SlotService;
import com.dictionary.web.view.layout.CenterVerticalLayout;
import com.dictionary.web.view.button.CustomButton;
import com.dictionary.web.view.layout.CustomVerticalLayout;
import com.dictionary.web.view.slot.SlotEditorForm;
import com.dictionary.web.view.StandardDefaultPicture;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLayout;

import javax.annotation.security.PermitAll;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Route(value = "/slots/:slotId/editor", layout = Home.class)
@PermitAll
public class SlotEditor extends CenterVerticalLayout implements RouterLayout, HasUrlParameter<String> {

    private final SlotService slotService;
    private final FilePropertyService filePropertyService;

    public SlotEditor(SlotService slotService, FilePropertyService filePropertyService) {
        this.slotService = slotService;
        this.filePropertyService = filePropertyService;
        setWidth(Size.PERCENT_100);
        setAlignItems(Alignment.CENTER);
    }

    private long getSlotIdFromRouteParameters(BeforeEvent event) {
        RouteParameters parameters = event.getRouteParameters();
        return Long.parseLong(parameters.get("slotId").orElseThrow(EntityNotFoundException::new));
    }

    private void clickSave(Slot slot, SlotEditorForm slotEditorForm) {
        slot.setName(slotEditorForm.getName());
        Optional.ofNullable(slotEditorForm.getPictureLoader().getPictureFile())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .ifPresent(file -> slot.setFileProperty(filePropertyService.saveMediaFile(file)));
        slotService.save(slot);
        Notification.show("edited");
        UI.getCurrent().navigate("/slots/" + slot.getId() + "/editor");
    }

    private Button createButton(Slot slot, SlotEditorForm slotEditorForm) {
        return CustomButton.builder()
                .name("save")
                .width(Size.PERCENT_100)
                .clickListener(() -> clickSave(slot, slotEditorForm))
                .build();
    }

    private SlotEditorForm createEditorForm(Slot slot) {
        return SlotEditorForm.builder()
                .name(slot.getName())
                .picture(Optional.ofNullable(slot.getFileWrapperId()).stream()
                        .map(filePropertyService::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(FileProperty::getMediaFile)
                        .map(PictureFile::new)
                        .findFirst()
                        .orElse(new StandardDefaultPicture().getPictureFile()))
                .build();
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        removeAll();
        Slot slot = slotService.findById(getSlotIdFromRouteParameters(event));
        SlotEditorForm slotEditorForm = createEditorForm(slot);
        add(CustomVerticalLayout.builder()
                .width(Size.PERCENT_50)
                .add(slotEditorForm.getWrapper())
                .add(createButton(slot, slotEditorForm))
                .build());
    }
}
