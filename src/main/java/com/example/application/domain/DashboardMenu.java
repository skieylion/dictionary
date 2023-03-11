package com.example.application.domain;

import java.util.List;

public enum DashboardMenu {
    SLOTS("slots"), CREATE("create");

    final String name;

    DashboardMenu(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final List<String> ITEMS = List.of(DashboardMenu.SLOTS.toString(), DashboardMenu.CREATE.toString());
}
