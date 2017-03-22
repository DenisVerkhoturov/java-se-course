package com.github.leo_scream.java_se_course.unit_03.task_02.components;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Controller {
    private final String i18nFaqUri = "com.github.leo_scream.java_se_course.unit_03.task_02.bundles.Locale";

    @FXML
    private ComboBox<Locale> locales;
    @FXML
    private Accordion faqAccordion;

    public void initialize() {
        locales.setItems(
            FXCollections.observableList(
                Arrays.asList(Locale.ENGLISH, new Locale("ru"), Locale.GERMAN)
            )
        );
        locales.setConverter(new StringConverter<Locale>() {
            @Override
            public String toString(Locale locale) {
                return locale.getDisplayLanguage(locale);
            }

            @Override
            public Locale fromString(String string) {
                return new Locale(string);
            }
        });
        locales.setOnAction(event -> renderFAQAccordion());
        Locale defaultLocale = locales.getItems().contains(Locale.getDefault()) ? Locale.getDefault() : Locale.ENGLISH;
        locales.getSelectionModel().select(defaultLocale);
        renderFAQAccordion();
    }

    private void renderFAQAccordion() {
        faqAccordion.getPanes().clear();
        ResourceBundle resources = ResourceBundle.getBundle(i18nFaqUri, locales.getValue());
        resources.keySet().stream()
            .map(property -> property.substring(0, property.indexOf('.')))
            .distinct()
            .forEach(
                identifier -> {
                    String question = resources.getString(identifier + ".question");
                    String answer = resources.getString(identifier + ".answer");
                    Text content = new Text(answer);
                    content.wrappingWidthProperty().bind(faqAccordion.widthProperty());
                    faqAccordion.getPanes().add(new TitledPane(question, content));
                }
            );
    }
}
