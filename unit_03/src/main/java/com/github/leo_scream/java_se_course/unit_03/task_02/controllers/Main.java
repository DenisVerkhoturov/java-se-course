package com.github.leo_scream.java_se_course.unit_03.task_02.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Main implements Initializable
{
    private final ObservableList<Locale> locales = FXCollections.observableArrayList(
        Locale.ENGLISH, new Locale("ru")
    );
    @FXML
    private ComboBox<Locale> languages;
    @FXML
    private Accordion faqAccordion;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        languages.setItems(locales);
        languages.getSelectionModel().select(Locale.getDefault());
        languages.setOnAction(event -> refreshFAQ(languages.getValue()));

        refreshFAQ(Locale.getDefault());
    }

    private void refreshFAQ(Locale locale)
    {
        final ResourceBundle resources = ResourceBundle.getBundle("bundles.Locale", locale);
        final Set<String> identifiers = new HashSet<>();
        faqAccordion.getPanes().clear();
        resources.keySet().forEach(
            property -> {
                String identifier = property.substring(0, property.indexOf('.'));

                if (!identifiers.contains(identifier)) {
                    String question = resources.getString(identifier + ".question");
                    String answer = resources.getString(identifier + ".answer");
                    faqAccordion.getPanes().add(renderEntry(question, answer));
                }
            }
        );
    }

    private TitledPane renderEntry(String question, String answer)
    {
        Text content = new Text(answer);
        content.wrappingWidthProperty().bind(faqAccordion.widthProperty());
        TitledPane entry = new TitledPane(question, content);
        entry.maxWidthProperty().bind(faqAccordion.widthProperty());
        entry.setWrapText(true);
        return entry;
    }
}
