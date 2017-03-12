package com.github.leo_scream.java_se_course.unit_03.task_02.stores;

import com.github.leo_scream.flux.AbstractStore;
import com.github.leo_scream.flux.Action;
import com.github.leo_scream.flux.Dispatcher;
import com.github.leo_scream.java_se_course.unit_03.task_02.actions.ToggleLocaleAction;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class LocaleStore extends AbstractStore {

    private static volatile LocaleStore instance;
    private final String i18nResourcesURI;
    private Locale currentLocale;
    private Set<Locale> locales;
    private ResourceBundle resources;

    private LocaleStore() {
        this.i18nResourcesURI = "com.github.leo_scream.java_se_course.unit_03.task_02.bundles.Locale";
        this.currentLocale = Locale.getDefault();
        this.locales = new HashSet<>(
            Arrays.asList(Locale.ENGLISH, new Locale("ru"), Locale.GERMAN)
        );
        this.resources = ResourceBundle.getBundle(this.i18nResourcesURI, this.currentLocale);
        Dispatcher.getInstance().register(this);
    }

    public static LocaleStore getInstance() {
        LocaleStore localInstance = instance;
        if (localInstance == null) {
            synchronized (LocaleStore.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new LocaleStore();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void processAction(Action action) {
        if (action instanceof ToggleLocaleAction) {
            processToggleLocaleAction((ToggleLocaleAction) action);
        }
    }

    private void processToggleLocaleAction(ToggleLocaleAction action) {
        Locale newLocale = action.getNewLocale();
        if (locales.contains(newLocale)) {
            currentLocale = action.getNewLocale();
            resources = ResourceBundle.getBundle(i18nResourcesURI, newLocale);
            publishOnChange();
        }
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public Stream<Locale> getLocales() {
        return locales.stream();
    }

    ResourceBundle getResources() {
        return resources;
    }
}
