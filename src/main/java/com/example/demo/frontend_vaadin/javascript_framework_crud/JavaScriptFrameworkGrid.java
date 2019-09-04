package com.example.demo.frontend_vaadin.javascript_framework_crud;

import com.example.demo.backend.model.JavaScriptFramework;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;

import javax.annotation.PostConstruct;

/**
 * Grid (table) of data
 */
@SpringComponent
public class JavaScriptFrameworkGrid extends Grid<JavaScriptFramework> {

    @PostConstruct
    private void init() {
        addColumn(JavaScriptFramework::getFrameworkName).setCaption("Framework Name")
                .setSortProperty("frameworkName");
    }

    public JavaScriptFramework getSelectedRow() {
        return asSingleSelect().getValue();
    }
}
