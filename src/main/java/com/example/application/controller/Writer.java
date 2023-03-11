package com.example.application.controller;

import com.example.application.component.Dashboard;
import com.example.application.domain.DashboardMenu;
import com.example.application.view.CenterVerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@Route(value = "/writer", layout = Home.class)
public class Writer extends CenterVerticalLayout implements RouterLayout {

    public Writer(Dashboard dashboardService) {
        dashboardService.setContent(DashboardMenu.CREATE, this);
    }

}