package com.github.leo_scream.java_se_course.unit_03.task_02.components;

import com.github.leo_scream.flux.Dispatcher;
import com.github.leo_scream.java_se_course.unit_03.task_02.actions.ToggleLocaleAction;
import com.github.leo_scream.java_se_course.unit_03.task_02.stores.LocaleStore;
import java.util.Locale;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Localization {

    private final LocaleStore localeStore;
    @FXML
    private ComboBox<Locale> languages;

    public Localization() {
        this.localeStore = LocaleStore.getInstance();
    }

    public void initialize() {
        languages.setItems(
            FXCollections.observableList(
                localeStore.getLocales().collect(Collectors.toList())
            )
        );
        languages.setConverter(new StringConverter<Locale>() {
            @Override
            public String toString(Locale locale) {
                return locale.getDisplayLanguage(locale);
            }

            @Override
            public Locale fromString(String string) {
                return new Locale(string);
            }
        });
        languages.getSelectionModel().select(localeStore.getCurrentLocale());
        languages.setOnAction(
            event -> Dispatcher.getInstance().dispatch(new ToggleLocaleAction(languages.getValue()))
        );
    }
}
