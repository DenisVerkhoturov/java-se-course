package com.github.leo_scream.java_se_course.unit_04.flux;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public interface Store {

    void processAction(Action action);

    void onChange(Runnable runnable);
}
