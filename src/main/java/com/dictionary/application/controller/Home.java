package com.dictionary.application.controller;

import com.dictionary.application.service.Navigator;
import com.dictionary.application.service.SlotService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.select.SelectVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import javax.annotation.security.PermitAll;
import java.util.List;

@PageTitle("Main")
@Route(value = "")
@PermitAll
public class Home extends AppLayout implements RouterLayout {
    private static final String APP_NAME = "EN";
    private final H1 title;

    {
        title = new H1(APP_NAME);
        title.getStyle().set("font-size", "var(--lumo-font-size-s)").set("margin", "0");
    }

    public Home(SlotService slotService) {
        int count = slotService.getCountOverdueCards();
        var menu = Navigator.createMenu(count);
        menu.getStyle().set("height", "39%");
        addToDrawer(menu);

        var vlOptions = new VerticalLayout();
        vlOptions.setHorizontalComponentAlignment(FlexComponent.Alignment.START);
        vlOptions.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        vlOptions.getStyle().set("height", "44%");
        vlOptions.getStyle().set("border-top", "1px solid #36485F");


        var checkbox = new Checkbox();
        checkbox.setLabel("dark");
        checkbox.setReadOnly(true);
        checkbox.setValue(true);
        vlOptions.add(new HorizontalLayout(checkbox));
        addToDrawer(vlOptions);

        // Создаем новые компоненты
        var vl = new VerticalLayout();
        vl.add(new TextField());
        vl.add(new Button("add draft"));
        vl.getStyle().set("border-top", "1px solid #36485F");

        // Выравнивание по центру
        vl.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Распределение пространства, чтобы элементы начинались снизу
        vl.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        vl.getStyle().set("height", "14%");
        addToDrawer(vl);

        Select<String> select = new Select<>();
        select.addThemeVariants(SelectVariant.LUMO_SMALL);
        select.setWidth("70px");
        select.setItems(List.of("EN", "DE"));
        select.setValue("EN");

        var hl = new HorizontalLayout(new Span("Dictionary"), select, new Span(" "));
        hl.setWidthFull();
        hl.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        //hl.getStyle().set("display", "flex");
        //hl.getStyle().set("justify-content", "space-between");
        addToNavbar(new DrawerToggle(), hl);
    }

}