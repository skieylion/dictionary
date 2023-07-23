package com.dictionary.application.controller;

import com.dictionary.application.domain.Card;
import com.dictionary.application.domain.Example;
import com.dictionary.application.domain.Slot;
import com.dictionary.application.repository.SlotRepository;
import com.dictionary.application.view.CardReader;
import com.dictionary.application.view.CustomButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.PermitAll;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


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