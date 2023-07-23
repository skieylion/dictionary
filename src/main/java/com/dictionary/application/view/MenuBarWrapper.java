package com.dictionary.application.view;

import com.dictionary.application.domain.CardContext;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;

public class MenuBarWrapper extends MenuBar {
    private final SubMenu subItems;

    public MenuBarWrapper(String label) {
        super();
        addThemeVariants(MenuBarVariant.LUMO_ICON, MenuBarVariant.LUMO_PRIMARY);
        addItem(label);
        subItems = addItem(new Icon(VaadinIcon.CHEVRON_DOWN)).getSubMenu();
    }

    public MenuBarWrapper add(String label, CardContext context) {
        subItems.addItem(label).addClickListener(listener -> {
            //NewCommand.execute();
            //CardContainer, Component, MenuButtons
        });
        return this;
    }
}

