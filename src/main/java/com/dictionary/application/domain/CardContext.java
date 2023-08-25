package com.dictionary.application.domain;

import com.dictionary.application.view.CardContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class CardContext {
    private CardContainer cardContainer;
    private MenuItem item;
    private Component component;
    private Component defaultComponent;
    private List<Button> buttons;
    private List<Button> defaultButtons;
    private Button button;
    private Container container;
    private String title;


    @Getter
    @AllArgsConstructor
    public static class Container {
        private VerticalLayout leftLayout;
        private VerticalLayout rightLayout;
        private HorizontalLayout layout;
    }
}
