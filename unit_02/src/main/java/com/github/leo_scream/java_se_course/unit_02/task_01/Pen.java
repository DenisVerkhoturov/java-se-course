package com.github.leo_scream.java_se_course.unit_02.task_01;

import java.awt.*;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Pen {

    private final Color color;
    private final int ink;

    Pen(Color color) {
        this.color = color;
        this.ink = 100;
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash = hash * 13 + color.hashCode();
        hash = hash * 13 + ink;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Pen other = (Pen) obj;
        if (!this.color.equals(other.color)) {
            return false;
        }
        if (this.ink != other.ink) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return getClass().getName() +
            "[color: " + color + ", " + "ink: " + ink + "]";
    }
}
