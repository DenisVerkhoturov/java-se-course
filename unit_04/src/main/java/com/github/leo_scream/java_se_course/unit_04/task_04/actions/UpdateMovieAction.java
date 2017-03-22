package com.github.leo_scream.java_se_course.unit_04.task_04.actions;

import com.github.leo_scream.java_se_course.unit_04.flux.AbstractAction;
import com.github.leo_scream.java_se_course.unit_04.task_04.business.Movie;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class UpdateMovieAction extends AbstractAction {
    private final Movie oldMovie;
    private final Movie newMovie;

    public UpdateMovieAction(Movie oldMovie, Movie newMovie) {
        super();
        this.oldMovie = oldMovie;
        this.newMovie = newMovie;
    }

    public Movie getOldMovie() {
        return oldMovie;
    }

    public Movie getNewMovie() {
        return newMovie;
    }

}
