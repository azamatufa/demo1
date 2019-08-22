package com.example.demo.frontend;

import com.example.demo.service.JavaScriptFrameworkService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringUI
public class MainUi extends UI {
    private VerticalLayout root = new VerticalLayout();

    @Autowired
    private JavaScriptFrameworkService javaScriptFrameworkService;

    @PostConstruct
    private void init() {
        System.out.println(javaScriptFrameworkService.hello());
    }

    @Override
    protected void init(VaadinRequest request) {
        System.out.println(javaScriptFrameworkService.getOne(1l));
//        Label label = new Label("Hello UI. jsf=");
        Label label = new Label("Hello UI. jsf=" + javaScriptFrameworkService.getOne(1l));
        root.addComponent(label);
        setContent(root);
    }
}
