package com.dictionary.web.controller;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import javax.annotation.security.PermitAll;

@Route("login")
@AnonymousAllowed
@PermitAll
public class Login extends VerticalLayout {
    public Login() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        var loginForm = new LoginForm();
        loginForm.setAction("login");
        add(loginForm);
    }
}
