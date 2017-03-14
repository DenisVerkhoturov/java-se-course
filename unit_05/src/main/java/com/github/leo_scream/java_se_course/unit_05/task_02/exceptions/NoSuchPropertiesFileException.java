package com.github.leo_scream.java_se_course.unit_05.task_02.exceptions;

import java.io.IOException;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class NoSuchPropertiesFileException extends IOException {

    public NoSuchPropertiesFileException(IOException e) {
        super(e);
    }
}
