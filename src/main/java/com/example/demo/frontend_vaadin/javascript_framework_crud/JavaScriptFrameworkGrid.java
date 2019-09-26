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
        addColumn(JavaScriptFramework::getFrameworkName).setCaption("Name")
                .setSortProperty("frameworkName");
        addColumn(JavaScriptFramework::getVersion).setCaption("Version")
                .setSortProperty("version");
        addColumn(JavaScriptFramework::getDeprecationDate).setCaption("Deprecation date")
                .setSortProperty("deprecationDate");
        addColumn(JavaScriptFramework::getHypeLevel).setCaption("Hype Level")
                .setSortProperty("hypeLevel");
    }

    public JavaScriptFramework getSelectedRow() {
        return asSingleSelect().getValue();
    }
}
