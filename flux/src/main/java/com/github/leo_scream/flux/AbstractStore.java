package com.github.leo_scream.flux;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public abstract class AbstractStore implements Store {

    private Set<Runnable> onChangeListeners = new HashSet<>();

    @Override
    public void onChange(Runnable runnable) {
        onChangeListeners.add(runnable);
    }

    protected void publishOnChange() {
        onChangeListeners.forEach(Runnable::run);
    }
}
