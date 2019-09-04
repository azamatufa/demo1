package com.example.demo.frontend_vaadin.javascript_framework_crud;

import com.example.demo.backend.model.HypeLevel;
import com.example.demo.backend.model.JavaScriptFramework;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Form to edit
 */
@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JavaScriptFrameworkForm extends FormLayout {

    private final Binder<JavaScriptFramework> binder = new BeanValidationBinder<>(JavaScriptFramework.class);
    private JavaScriptFrameworkLogic logic;
    private JavaScriptFramework currentJavaScriptFramework;

    private TextField frameworkName;
    private TextField version;
    private DateField deprecationDate;
    private CheckBoxGroup<HypeLevel> hypeLevel;

    private Button save;
    private Button discard;
    private Button cancel;
    private Button delete;

    private JavaScriptFrameworkForm() {
    }

    @Autowired
    public JavaScriptFrameworkForm(JavaScriptFrameworkLogic logic) {
        this.logic = logic;
    }

    @PostConstruct
    private void init() {
        frameworkName = new TextField();
        version = new TextField();
        deprecationDate = new DateField();
        hypeLevel = new CheckBoxGroup<>();

        save = new Button("Save", e -> onSave());
        discard = new Button("Discard");
        cancel = new Button("Cancel");
        delete = new Button("Delete");

        binder.forField(frameworkName).bind("frameworkName");
        binder.forField(deprecationDate).bind("deprecationDate");

        // hypeLevel.setItemCaptionGenerator();
        binder.forField(hypeLevel).bind("hypeLevel");

        binder.addStatusChangeListener(this::updateButtons);

    }

    private void onSave() {
        if (binder.writeBeanIfValid(currentJavaScriptFramework)) {
            logic.save(currentJavaScriptFramework);
        }
    }

    public void edit(JavaScriptFramework javaScriptFramework) {
        currentJavaScriptFramework = javaScriptFramework;
        setupData();
        delete.setEnabled(javaScriptFramework != null && javaScriptFramework.getId() != -1);
    }

    private void setupData() {
        if (currentJavaScriptFramework != null) {
            binder.readBean(currentJavaScriptFramework);
        } else {
            binder.removeBean();
        }
    }

    private void updateButtons(StatusChangeEvent event) {
        boolean changes = event.getBinder().hasChanges();
        boolean validationErrors = event.hasValidationErrors();

        save.setEnabled(!validationErrors && changes);
        discard.setEnabled(changes);
    }


}
