package com.example.demo.frontend_vaadin.javascript_framework_crud;

import com.example.demo.backend.model.JavaScriptFramework;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Logic (presenter) for View,Grid,Form. Tie them together;
 */
@SpringComponent
public class JavaScriptFrameworkLogic {


    private JavaScriptFrameworkView view;
    private final JavaScriptFrameworkRestClient restClient;

    @Autowired
    public JavaScriptFrameworkLogic(JavaScriptFrameworkRestClient restClient) {
        this.restClient = restClient;
    }

    public void init(JavaScriptFrameworkView view) {
        this.view = view;
        edit(null);
    }


    public void edit(JavaScriptFramework javaScriptFramework) {
        // if role is admin...
        view.edit(javaScriptFramework);
    }

    public void save(JavaScriptFramework javaScriptFramework) {
        JavaScriptFramework saved = restClient.save(javaScriptFramework);
        view.showNotification(javaScriptFramework.getFrameworkName() + " ("
                + javaScriptFramework.getId() + ") updated");
        view.clearSelection();
        view.edit(null);
        view.refreshGrid();
    }
}

