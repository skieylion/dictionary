package com.example.application.controller;

import com.example.application.component.Dashboard;
import com.example.application.domain.DashboardMenu;
import com.example.application.view.CenterVerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@Route(value = "/slots", layout = Home.class)
public class Slots extends VerticalLayout implements RouterLayout {

    public Slots(Dashboard dashboardService) {
        dashboardService.setContent(DashboardMenu.SLOTS, this);
    }
}
