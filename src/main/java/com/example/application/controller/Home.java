package com.example.application.controller;

import com.example.application.component.Dashboard;
import com.example.application.domain.DashboardMenu;
import com.example.application.view.CenterVerticalLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@PageTitle("Main")
@Route(value = "")
public class Home extends AppLayout implements RouterLayout {

    public Home(Dashboard dashboard) {
        dashboard.init(this);
        dashboard.getTabs().addSelectedChangeListener(event -> {
            dashboard.setContent(DashboardMenu.valueOf(event.getSelectedTab().getLabel().toUpperCase()), new CenterVerticalLayout());
        });
    }
}