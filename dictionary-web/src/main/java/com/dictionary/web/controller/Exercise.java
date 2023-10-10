package com.dictionary.web.controller;

import com.dictionary.core.domain.Example;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
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
