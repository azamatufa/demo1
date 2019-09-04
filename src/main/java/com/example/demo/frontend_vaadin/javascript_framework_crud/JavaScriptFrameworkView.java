package com.example.demo.frontend_vaadin.javascript_framework_crud;

import com.example.demo.frontend_vaadin.MainUI;
import com.example.demo.backend.model.JavaScriptFramework;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * View (main layout) of JavaScriptFrameWorks crud.
 */
@SpringView(name = JavaScriptFrameworkView.PATH, ui = MainUI.class)
public class JavaScriptFrameworkView extends CssLayout implements View {
    public static final String PATH = "javascript_frameworks_crud";

    @Autowired
    private JavaScriptFrameworkLogic logic;
    @Autowired
    private JavaScriptFrameworkGrid grid;
    private ConfigurableFilterDataProvider<JavaScriptFramework, Void, String> filterDataProvider;
    @Autowired
    private JavaScriptFrameworkDataProvider dataProvider;
    @Autowired
    private JavaScriptFrameworkForm form;

    private TextField filterTextField;
    private Button btnNew;
    private Button btnEdit;

    @PostConstruct
    private void init() {

        btnEdit = new Button("Edit");
        btnEdit.addClickListener(e -> logic.edit(grid.getSelectedRow()));

        filterDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(filterDataProvider);

        VerticalLayout barAndGrid = new VerticalLayout();
        barAndGrid.addComponent(createTopBar());
        barAndGrid.addComponent(grid);

        addComponent(barAndGrid);
        addComponent(form);
        addComponent(new Label("Demo"));

        logic.init(this);

    }

    private HorizontalLayout createTopBar() {
        filterTextField = new TextField();
        filterTextField.addValueChangeListener(e -> filterDataProvider.setFilter(e.getValue()));
        btnNew = new Button("New");
        HorizontalLayout hl = new HorizontalLayout();
        hl.addComponents(filterTextField, btnNew);
        return hl;
    }


    public void edit(JavaScriptFramework javaScriptFramework) {
        if (javaScriptFramework != null) {
            form.setEnabled(true);
        } else {
            form.setEnabled(false);
        }
        form.edit(javaScriptFramework);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("enter " + event.getParameters());
    }
}
