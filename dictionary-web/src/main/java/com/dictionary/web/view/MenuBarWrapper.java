package com.dictionary.web.view;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;

public class MenuBarWrapper extends MenuBar {
    private final SubMenu subItems;
    private final MenuItem menuItem;

    public MenuBarWrapper(String label) {
        super();
        addThemeVariants(MenuBarVariant.LUMO_ICON, MenuBarVariant.LUMO_PRIMARY);
        menuItem = super.addItem(label);
        subItems = super.addItem(new Icon(VaadinIcon.CHEVRON_DOWN)).getSubMenu();
    }

    public MenuBarWrapper() {
        this("");
    }

    @Override
    public MenuItem addItem(String label) {
        return subItems.addItem(label);
    }

    public void setTitle(String label) {
        menuItem.setText(label);
    }
}
