package com.dictionary.web.domain;

import com.dictionary.web.view.CardContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
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
    private CardContextType contextType;
    private ContextContainer contextContainer;

    @Getter
    @AllArgsConstructor
    public static class Container {
        private VerticalLayout leftLayout;
        private HorizontalLayout upLayout;
        private VerticalLayout layout;
    }
}
