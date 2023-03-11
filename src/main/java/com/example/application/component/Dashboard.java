package com.example.application.component;

import com.example.application.domain.DashboardMenu;
import com.example.application.view.CardReader;
import com.example.application.view.CardWriter;
import com.example.application.view.CenterVerticalLayout;
import com.example.application.view.SlotList;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import java.util.Map;
import java.util.function.Supplier;

@org.springframework.stereotype.Component
public class Dashboard {

    private static final Map<DashboardMenu, Supplier<Component>> DASHBOARD_COMPONENT = Map.of(
            DashboardMenu.CREATE, SlotList::new,
            DashboardMenu.SLOTS, SlotList::new
    );

    private static final String APP_NAME = "English vocabulary";
    private Tabs tabs;
    private AppLayout appLayout;

    public void init(AppLayout appLayout) {
        this.appLayout = appLayout;
        tabs = menu();
        appLayout.addToDrawer(tabs);
        appLayout.addToNavbar(new DrawerToggle(), title());
    }

    public Tabs getTabs() {
        return tabs;
    }

    public void setContent(DashboardMenu item, VerticalLayout layout) {
        layout.removeAll();
        layout.add(DASHBOARD_COMPONENT.get(item).get());
        appLayout.setContent(layout);
        setSelectedDashboardMenu(item);
    }

    private void setSelectedDashboardMenu(DashboardMenu currentItem) {
        for (int i = 0; i < DashboardMenu.ITEMS.size(); i++) {
            if (DashboardMenu.valueOf(DashboardMenu.ITEMS.get(i).toUpperCase()) == currentItem) {
                tabs.setSelectedIndex(i);
            }
        }
    }

    private static H1 title() {
        H1 title = new H1(APP_NAME);
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        return title;
    }

    private static Tabs menu() {
        Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        DashboardMenu.ITEMS.stream()
                .map(label -> {
                    Tab tab = new Tab();
                    tab.setLabel(label);
                    return tab;
                }).forEach(tabs::add);
        return tabs;
    }
}
