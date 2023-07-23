package com.dictionary.application.service;

import com.dictionary.application.controller.Home;
import com.dictionary.application.controller.NewSlot;
import com.dictionary.application.controller.Slots;
import com.dictionary.application.controller.Writer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

public enum Navigator {
    SLOTS(0, Slots.class), NEW_SLOT(1, NewSlot.class), WRITER(2, Writer.class);
    private static Tabs tabs;
    private final int index;
    private final Class<? extends Component> clazz;

    Navigator(int index, Class<? extends Component> clazz) {
        this.index = index;
        this.clazz = clazz;
    }

    public int getIndex() {
        return index;
    }

    public void select() {
        if (tabs != null) {
            tabs.setSelectedIndex(this.index);
        }
    }

    public static void navigate(Navigator navigator) {
        UI.getCurrent().navigate(navigator.clazz);
    }

    public void refresh() {
        UI.getCurrent().navigate(Home.class);
        UI.getCurrent().navigate(clazz);
    }

    public static Tabs createMenu(int slotsCount) {
        tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.add(new Tab(new Span("drafts (4)")));
        tabs.add(createNewSlot(), createTabSlots(slotsCount), new Tab(new Span("slot hub")));
        tabs.add(new Tab(new Span("hints (10)")));
        tabs.add(new Tab(new Span("materials (10)")));
        tabs.add(new Tab(new Span("settings")));
        tabs.add(new Tab(new Span("profile")));
        tabs.add(new Tab(new Span("tariff (free)")));
        tabs.addSelectedChangeListener(event -> {
            event.getSelectedTab().getId().ifPresent(id -> Navigator.navigate(Navigator.valueOf(id)));
        });
        return tabs;
    }

    private static Tab createNewCard() {
        var tabNewCard = new Tab(new Span("new card"));
        tabNewCard.setId(Navigator.WRITER.name());
        return tabNewCard;
    }

    private static Tab createTabSlots(int count) {
        Span counter = new Span(String.format(" (%d) ", count));
        //counter.getElement().getThemeList().add("badge error primary pill small");
        counter.getStyle().set("color", "red");
        var tabSlots = new Tab(new Span("my slots "), counter);
        tabSlots.setId(Navigator.SLOTS.name());
        return tabSlots;
    }

    private static Tab createNewSlot() {
        var tabNewSlot = new Tab(new Span("new slot"));
        tabNewSlot.setId(Navigator.NEW_SLOT.name());
        return tabNewSlot;
    }
}
