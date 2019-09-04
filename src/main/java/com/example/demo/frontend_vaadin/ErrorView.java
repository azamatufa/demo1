package com.example.demo.frontend_vaadin;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author almukhametovar
 */
@SpringView
public class ErrorView extends VerticalLayout implements View {
    private static final Logger logger = LoggerFactory.getLogger(ErrorView.class);

    private TextField textField = new TextField("Error view");

    @Autowired
    public ErrorView() {
        Label label = new Label("Error. View not found");
        label.addStyleName(ValoTheme.LABEL_FAILURE);
        addComponent(label);
        addComponent(textField);
    }

}
