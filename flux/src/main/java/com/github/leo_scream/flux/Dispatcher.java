package com.github.leo_scream.flux;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Dispatcher {

    private static volatile Dispatcher instance;
    private final Set<Store> stores;

    private Dispatcher() {
        stores = new HashSet<>();
    }

    public void dispatch(final Action action) {
        stores.forEach(store -> store.processAction(action));
    }

    public void register(final Store store) {
        stores.add(store);
    }

    public static Dispatcher getInstance() {
        Dispatcher localInstance = instance;
        if (localInstance == null) {
            synchronized (Dispatcher.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new Dispatcher();
                }
            }
        }
        return localInstance;
    }
}
