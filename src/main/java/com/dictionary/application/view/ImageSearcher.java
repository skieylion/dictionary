package com.dictionary.application.view;

import com.dictionary.application.domain.Size;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;


public class ImageSearcher extends VerticalLayout {

    public ImageSearcher() {

        ProgressBar spinner = new ProgressBar();
        spinner.setIndeterminate(true);
        spinner.setVisible(true);
        spinner.setWidth(Size.PERCENT_50);

        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        flexLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        flexLayout.add(createPicture());
        flexLayout.add(createPicture());
        flexLayout.add(createPicture());
        flexLayout.add(createPicture());
        flexLayout.add(createPicture());
        flexLayout.add(createPicture());

        flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        Scroller scroller = createScroller(flexLayout);

        TextField textField = new TextField();
        textField.setWidth(Size.PERCENT_50);

        var vl = new VerticalLayout(textField, spinner);
        vl.setSpacing(false);
        vl.setMargin(false);
        vl.setPadding(false);
        vl.setWidthFull();
        vl.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(vl);
        add(scroller);

        setWidthFull();
        setMargin(false);
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

    }

    private static Scroller createScroller(FlexLayout flexLayout) {
        Scroller scroller = new Scroller();
        scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
        scroller.setWidthFull();
        scroller.setContent(flexLayout);
        scroller.setHeight(Size.PX_300);
        return scroller;
    }

    private static Picture createPicture() {
        Picture picture = new MiniDefaultPicture();
        picture.getStyle().set("border-radius", "10px");
        picture.getStyle().set("width", "210px");
        picture.getStyle().set("height", "140px");
        picture.getStyle().set("padding", "5px");
        return picture;
    }
}
