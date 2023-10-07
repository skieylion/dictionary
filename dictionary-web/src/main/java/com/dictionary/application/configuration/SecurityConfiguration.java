package com.dictionary.application.configuration;

import com.dictionary.application.controller.Login;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, Login.class);
    }

    @Bean
    UserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager(User.withUsername("user")
                .password("$2a$04$iGiZUapXEGxK49I9TAs75ue5NTMAwpOrjT7Ko8Oz/rjSKKYvGBSNS")
                .roles("USER").build());
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
}
