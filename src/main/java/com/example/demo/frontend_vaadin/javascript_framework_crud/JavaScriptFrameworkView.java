package com.example.demo.frontend_vaadin.javascript_framework_crud;

import com.example.demo.backend.model.JavaScriptFramework;
import com.example.demo.frontend_vaadin.MainUI;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * View (main layout)
 */
@SpringView(name = JavaScriptFrameworkView.PATH, ui = MainUI.class)
public class JavaScriptFrameworkView extends HorizontalLayout implements View {
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

    @PostConstruct
    private void init() {
        btnNew = new Button("New", e->onNew());

        filterDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(filterDataProvider);
        grid.asSingleSelect().addValueChangeListener(event -> {
            onEdit();
        });

        VerticalLayout barAndGrid = new VerticalLayout();
        barAndGrid.addComponent(createTopBar());
        barAndGrid.addComponent(grid);

        addComponent(barAndGrid);
        addComponent(form);

        logic.init(this);

    }

    private void onNew() {
        edit(new JavaScriptFramework());
    }

    private void onEdit() {
        JavaScriptFramework selectedRow = grid.getSelectedRow();
        logic.edit(selectedRow);
    }

    private HorizontalLayout createTopBar() {
        filterTextField = new TextField("Filter");
        filterTextField.addValueChangeListener(e -> filterDataProvider.setFilter(e.getValue()));

        HorizontalLayout hl = new HorizontalLayout();
        hl.addComponents(filterTextField, btnNew);
        return hl;
    }


    public void edit(JavaScriptFramework javaScriptFramework) {
        if (javaScriptFramework != null) {
            form.setVisible(true);
        } else {
            form.setVisible(false);
        }
        form.edit(javaScriptFramework);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("enter " + event.getParameters());
    }

    public void showNotification(String msg) {
        Notification.show("Сообщение", msg, Notification.Type.TRAY_NOTIFICATION);
    }

    public void clearSelection() {
        grid.getSelectionModel().deselectAll();

    }

    public void refreshGrid() {
        dataProvider.refreshAll();
    }
}
