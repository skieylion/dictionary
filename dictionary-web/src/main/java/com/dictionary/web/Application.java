package com.dictionary.web;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@SpringBootApplication
@Theme(variant = Lumo.DARK)
@NpmPackage(value = "line-awesome", version = "1.3.0")
@MapperScan(basePackages = "com.dictionary.core.repository")
@EnableFeignClients(basePackages = "com.dictionary.core.repository")
@CssImport("./styles/styles.css")
@PropertySource({"classpath:application-web.properties", "classpath:application-core.properties"})
@ComponentScan(basePackages = {"com.dictionary.web", "com.dictionary.core"})
@EntityScan(basePackages = {"com.dictionary.web", "com.dictionary.core"})
@EnableJpaRepositories(basePackages = {"com.dictionary.core"})
public class Application implements AppShellConfigurator {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
