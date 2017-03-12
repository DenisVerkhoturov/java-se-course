package com.github.leo_scream.java_se_course.unit_03.task_02.components;

import com.github.leo_scream.java_se_course.unit_03.task_02.stores.FAQStore;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class FAQ {

    private final FAQStore faqStore;
    @FXML
    private Accordion faqAccordion;

    public FAQ() {
        this.faqStore = FAQStore.getInstance();
    }

    public void initialize() {
        faqStore.onChange(this::renderFAQAccordion);
        renderFAQAccordion();
    }

    private void renderFAQAccordion() {
        faqAccordion.getPanes().clear();
        faqStore.getFaqEntries().forEach(entry -> {
            Text content = new Text(entry.getAnswer());
            content.wrappingWidthProperty().bind(faqAccordion.widthProperty());
            faqAccordion.getPanes().add(new TitledPane(entry.getQuestion(), content));
        });
    }
}
