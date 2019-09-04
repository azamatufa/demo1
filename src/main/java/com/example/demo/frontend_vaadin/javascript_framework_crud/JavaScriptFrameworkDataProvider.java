package com.example.demo.frontend_vaadin.javascript_framework_crud;

import com.example.demo.backend.model.JavaScriptFramework;
import com.vaadin.data.provider.DataProvider;

/**
 * DataProvider is used as datasource by {@link com.vaadin.ui.Grid}
 *
 */
public interface JavaScriptFrameworkDataProvider extends DataProvider<JavaScriptFramework, String> {
}
