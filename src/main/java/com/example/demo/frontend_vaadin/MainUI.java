package com.example.demo.frontend_vaadin;

import com.example.demo.frontend_vaadin.javascript_framework_crud.JavaScriptFrameworkView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "ui")
public class MainUI extends UI {

    private Navigator navigator;
    private VerticalLayout root;
    private Panel panel;

    @Autowired
    private SpringViewProvider springViewProvider;


    @Override
    protected void init(VaadinRequest request) {
        root = new VerticalLayout();
        panel = new Panel();
        panel.setSizeFull();

        navigator = new Navigator(this, panel);
        navigator.addView("", ErrorView.class);
        navigator.setErrorView(ErrorView.class);
        navigator.addProvider(springViewProvider);

        navigator.navigateTo(JavaScriptFrameworkView.PATH);
        root.addComponent(new Label("JavaScript Demo App"));
        root.addComponent(panel);

        setContent(root);
    }
}
