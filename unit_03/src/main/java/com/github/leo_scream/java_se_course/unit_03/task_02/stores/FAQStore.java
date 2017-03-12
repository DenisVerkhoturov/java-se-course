package com.github.leo_scream.java_se_course.unit_03.task_02.stores;

import com.github.leo_scream.flux.AbstractStore;
import com.github.leo_scream.flux.Action;
import com.github.leo_scream.flux.Dispatcher;
import com.github.leo_scream.java_se_course.unit_03.task_02.business.FAQEntry;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class FAQStore extends AbstractStore {

    private static volatile FAQStore instance;
    private final Set<FAQEntry> faqEntries;

    private FAQStore() {
        this.faqEntries = new HashSet<>();
        Dispatcher.getInstance().register(this);
        LocaleStore.getInstance().onChange(this::loadEntries);
        loadEntries();
    }

    public static FAQStore getInstance() {
        FAQStore localInstance = instance;
        if (localInstance == null) {
            synchronized (FAQStore.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new FAQStore();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void processAction(Action action) {
    }

    private void loadEntries() {
        final Set<String> identifiers = new HashSet<>();
        final ResourceBundle resources = LocaleStore.getInstance().getResources();
        faqEntries.clear();
        resources.keySet().forEach(
            property -> {
                String identifier = property.substring(0, property.indexOf('.'));

                if (!identifiers.contains(identifier)) {
                    String question = resources.getString(identifier + ".question");
                    String answer = resources.getString(identifier + ".answer");
                    faqEntries.add(new FAQEntry(question, answer));
                }
            }
        );
        this.publishOnChange();
    }

    public Stream<FAQEntry> getFaqEntries() {
        return faqEntries.stream();
    }
}
