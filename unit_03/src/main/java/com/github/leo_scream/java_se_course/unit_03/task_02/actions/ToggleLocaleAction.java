package com.github.leo_scream.java_se_course.unit_03.task_02.actions;

import com.github.leo_scream.flux.AbstractAction;
import java.util.Locale;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class ToggleLocaleAction extends AbstractAction {

    private final Locale newLocale;

    public ToggleLocaleAction(Locale locale) {
        newLocale = locale;
    }

    public Locale getNewLocale() {
        return newLocale;
    }
}
