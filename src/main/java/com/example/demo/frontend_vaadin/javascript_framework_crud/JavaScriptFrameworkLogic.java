package com.example.demo.frontend_vaadin.javascript_framework_crud;

import com.example.demo.backend.model.JavaScriptFramework;
import com.vaadin.spring.annotation.SpringComponent;

/**
 * Logic (presenter) for View,Grid,Form. Tie them together;
 */
@SpringComponent
public class JavaScriptFrameworkLogic {


    private JavaScriptFrameworkView view;

    public void init(JavaScriptFrameworkView view) {
        this.view = view;
        editProduct(null);
    }

    private void editProduct(JavaScriptFramework javaScriptFramework) {

    }

    public void edit(JavaScriptFramework javaScriptFramework) {
        // if role is admin...
        view.edit(javaScriptFramework);
    }

    public void save(JavaScriptFramework javaScriptFramework) {

    }
}

