package com.dictionary.application.view;

import com.dictionary.application.domain.CardContext;
import com.dictionary.application.service.command.NewCardCommand;
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
        menuItem = addItem(label);
        subItems = addItem(new Icon(VaadinIcon.CHEVRON_DOWN)).getSubMenu();
    }

    public MenuBarWrapper() {
        this("");
    }

    public MenuBarWrapper add(String label, CardContext context) {
        var item = subItems.addItem(label);
        context.setItem(item);
        item.addClickListener(listener -> new NewCardCommand(context).execute());
        return this;
    }

    public void setTitle(String label) {
        menuItem.setText(label);
    }
}


