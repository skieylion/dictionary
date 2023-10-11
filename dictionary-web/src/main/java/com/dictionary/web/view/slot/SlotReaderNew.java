package com.dictionary.web.view.slot;

import com.dictionary.core.domain.Size;
import com.dictionary.core.domain.SlotStat;
import com.dictionary.web.view.ComponentBuilder;
import com.dictionary.web.view.MiniDefaultPicture;
import com.dictionary.web.view.Picture;
import com.dictionary.web.view.button.CustomButton;
import com.dictionary.web.view.layout.VerticalHeaderLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Stream;

import lombok.Getter;

public class SlotReaderNew extends VerticalHeaderLayout {
    {
        setSpacing(false);
        setPadding(false);
        setMargin(false);
        setWidth(Size.PX_300);
        setHeight(Size.PX_300);
        getStyle().set("border-color", "#504F51");
        getHeader().getStyle().set("background-color", "#504F51");
        getLayout().setSpacing(false);
        getLayout().setMargin(false);
        getLayout().setPadding(false);
    }

    private static final String TEMPLATE = "";

    private final Picture picture;
    private final Div statisticDiv;

    @Getter
    private final Button viewButton;
    @Getter
    private final Button academyButton;
    @Getter
    private final MenuItem editButton;
    @Getter
    private final MenuItem removeButton;

    public SlotReaderNew(String caption) {
        super(caption);
        statisticDiv = new Div();
        statisticDiv.getElement().getStyle().set("float", "right");
        picture = new MiniDefaultPicture();
        picture.setWidthFull();
        picture.setHeight("212px");
        picture.getStyle().set("border-bottom", "3px solid #504F51"); // 3px solid #212A36
        HorizontalLayout buttonHorizontal =
                ComponentBuilder.builder(HorizontalLayout.class)
                        .width(Size.PERCENT_100)
                        .emptyIndent(false, false)
                        .build();
        buttonHorizontal.getStyle().set("padding", "5px");
        viewButton = CustomButton.builder().icon(VaadinIcon.EYE).build();
        academyButton = CustomButton.builder().icon(VaadinIcon.ACADEMY_CAP).build();

        MenuBar menuBar = new MenuBar();
        menuBar.getStyle().set("position", "relative");
        menuBar.getStyle().set("top", "2px");
        menuBar.getStyle().set("left", "-2px");
        MenuItem settings = menuBar.addItem(new Icon(VaadinIcon.MENU));
        menuBar.addThemeVariants(MenuBarVariant.LUMO_ICON, MenuBarVariant.LUMO_PRIMARY);
        SubMenu settingsSubMenu = settings.getSubMenu();
        editButton = settingsSubMenu.addItem("Edit");
        removeButton = settingsSubMenu.addItem("Remove");

        buttonHorizontal.add(viewButton, academyButton, menuBar, statisticDiv);
        add(picture);
        add(buttonHorizontal);
    }

    public void setStatistic(SlotStat stat) {
        statisticDiv.removeAll();
        Deque<String> colors = new ArrayDeque<>(Arrays.asList("white", "green", "#FFBF00", "red"));
        Stream.of(
                        stat.getTotalCount(),
                        stat.getStudiedCount(),
                        stat.getWaitingCount(),
                        stat.getOverdueCount())
                .map(v -> new Paragraph(String.format(" %d ", v)))
                .peek(
                        p -> {
                            p.getStyle().set("float", "right");
                            // p.getStyle().set("background-color", "white");
                            p.getStyle().set("color", colors.pop());
                            p.getStyle().set("font-weight", "bold");
                            p.getStyle().set("white-space", "pre");
                        })
                .forEach(statisticDiv::add);
    }
}
