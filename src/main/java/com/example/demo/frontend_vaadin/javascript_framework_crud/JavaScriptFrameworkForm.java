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
import org.vaadin.dialogs.ConfirmDialog;

import javax.annotation.PostConstruct;

/**
 * Form
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
    private ComboBox<HypeLevel> hypeLevel;

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
        frameworkName = new TextField("Name");
        version = new TextField("Version");
        deprecationDate = new DateField("Deprecation Date");
        hypeLevel = new ComboBox<>("Hype Level");

        save = new Button("Save", e -> onSave());
        discard = new Button("Discard", e -> onDiscard());
        cancel = new Button("Cancel", e -> onCancel());
        delete = new Button("Delete", e -> onDelete());

        hypeLevel.setItems(HypeLevel.values());

        binder.forField(frameworkName).bind("frameworkName");
        binder.forField(version).bind("version");

        binder.bind(deprecationDate, JavaScriptFramework::getDeprecationDate, JavaScriptFramework::setDeprecationDate);

        binder.forField(hypeLevel).bind("hypeLevel");

        binder.addStatusChangeListener(this::updateButtonsVisibility);

        addComponent(frameworkName);
        addComponent(version);
        addComponent(deprecationDate);
        addComponent(hypeLevel);

        addComponent(save);
        addComponent(discard);
        addComponent(delete);
        addComponent(cancel);

    }

    private void onDelete() {
        ConfirmDialog.show(UI.getCurrent(), "Are you sure?", dialog -> {
            if (dialog.isConfirmed()) {
                logic.delete(currentJavaScriptFramework);
            }
        });
    }

    private void onCancel() {
        logic.edit(null);
    }

    private void onDiscard() {
        binder.readBean(currentJavaScriptFramework);
    }

    private void onSave() {
        if (binder.writeBeanIfValid(currentJavaScriptFramework)) {
            logic.save(currentJavaScriptFramework);
        }
    }

    public void edit(JavaScriptFramework javaScriptFramework) {
        currentJavaScriptFramework = javaScriptFramework;
        setupData();
        delete.setEnabled(javaScriptFramework != null && javaScriptFramework.getId() != null);
    }

    private void setupData() {
        if (currentJavaScriptFramework != null) {
            binder.readBean(currentJavaScriptFramework);
        } else {
            binder.removeBean();
        }
    }

    private void updateButtonsVisibility(StatusChangeEvent event) {
        boolean changes = event.getBinder().hasChanges();
        boolean validationErrors = event.hasValidationErrors();

        save.setEnabled(!validationErrors && changes);
        discard.setEnabled(changes);
    }


}
