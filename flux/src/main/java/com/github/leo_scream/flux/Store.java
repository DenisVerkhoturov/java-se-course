package com.github.leo_scream.flux;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public interface Store {

    void processAction(Action action);

    void onChange(Runnable runnable);
}
