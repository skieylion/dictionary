package com.dictionary.application.controller;

import com.dictionary.application.domain.Example;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.PermitAll;


@Route(value = "/exercises", layout = Home.class)
@AllArgsConstructor
@PermitAll
public class Exercise extends VerticalLayout implements RouterLayout, HasUrlParameter<String> {

    private final Example exampleDefault = new Example();

    @Value("${app.intervals}")
    private Integer[] intervals;

    {
        exampleDefault.setText("...");
    }

    @Override
    @Transactional
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        setAlignItems(Alignment.CENTER);
        QueryParameters queryParameters = event.getLocation().getQueryParameters();
        System.out.println(queryParameters.getQueryString());
    }
}