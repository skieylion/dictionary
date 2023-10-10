package com.dictionary.web.controller;

import com.dictionary.core.domain.PictureFile;
import com.dictionary.core.domain.Slot;
import com.dictionary.core.domain.SlotStat;
import com.dictionary.web.domain.dto.SlotCreatorDto;
import com.dictionary.web.domain.dto.SlotDto;
import com.dictionary.web.service.FilePropertyService;
import com.dictionary.web.service.Navigator;
import com.dictionary.web.service.mapper.SlotMapper;
import com.dictionary.core.repository.SlotStatRepository;
import com.dictionary.web.service.SlotService;
import com.dictionary.web.view.slot.SlotCreator;
import com.dictionary.web.view.slot.SlotReader;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Optional;

@Route(value = "/slots", layout = Home.class)
@PermitAll
public class Slots extends FlexLayout implements RouterLayout {
    private final SlotService slotService;
    private final SlotMapper slotMapper;
    private final SlotStatRepository slotStatRepository;
    private final FilePropertyService filePropertyService;

    public Slots(SlotService slotService, SlotMapper slotMapper, SlotStatRepository slotStatRepository, FilePropertyService filePropertyService) {
        this.slotService = slotService;
        this.slotMapper = slotMapper;
        this.slotStatRepository = slotStatRepository;
        this.filePropertyService = filePropertyService;
        Navigator.SLOTS.select();
        draw();
    }

    private void styles(FlexLayout layout) {
        layout.removeAll();
        layout.setWidthFull();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
    }

    private List<SlotDto> findAll() {
        List<Slot> slotList = slotService.findAll();
        List<SlotStat> slotStatList = slotStatRepository.getSlotStat();
        return slotMapper.convert(slotList, slotStatList);
    }

    private void addClickListener(SlotCreatorDto slotDto) {
        Slot slot = new Slot();
        slot.setName(slotDto.getName());
        Optional.ofNullable(slotDto.getFile()).stream()
                .map(filePropertyService::saveMediaFile)
                .forEach(slot::setFileProperty);
        slotService.save(slot);
    }

    private SlotCreator createSlotCreator() {
        SlotCreator slotCreator = new SlotCreator();
        slotCreator.getWrapper().getStyle().set("margin", "10px");
        slotCreator.addClickListener(this::addClickListener);
        return slotCreator;
    }

    private VerticalLayout createSlotReaderNew(SlotDto slot) {
        SlotReader slotReader = new SlotReader(slot.getName());
        slotReader.setStatistic(slot.getSlotStat());
        Optional.ofNullable(slot.getFileId()).stream()
                .map(filePropertyService::findByFilePropertyId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(PictureFile::new)
                .forEach(slotReader::setPicture);
        slotReader.getViewButton().addClickListener(l -> UI.getCurrent().navigate(Cards.class, slot.getId()));
        slotReader.getEditButton().addClickListener(l -> UI.getCurrent().navigate("/slots/" + slot.getId() + "/editor"));
        slotReader.getAcademyButton().addClickListener(l -> UI.getCurrent().navigate("/slots/" + slot.getId() + "/student"));
        slotReader.getRemoveButton().addClickListener(l -> {
            slotService.delete(slot.getId());
        });
        slotReader.getCreateCardButton().addClickListener(l -> UI.getCurrent().navigate("/slots/" + slot.getId() + "/cards/writer"));
        return slotReader;
    }


    private void draw() {
        styles(this);
        findAll().stream().map(this::createSlotReaderNew).peek(div -> div.getStyle().set("margin", "10px")).forEach(this::add);
    }

    private void dialog(Runnable confirm) {
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setText("Would you like to delete this slot ?");
        dialog.setCancelable(true);
        dialog.setCancelText("No");
        dialog.setConfirmText("Yes");
        dialog.addConfirmListener(event -> confirm.run());
        dialog.open();
    }
}
