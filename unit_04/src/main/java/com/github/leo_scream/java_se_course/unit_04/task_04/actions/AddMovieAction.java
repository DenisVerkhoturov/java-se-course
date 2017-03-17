package com.github.leo_scream.java_se_course.unit_04.task_04.actions;

import com.github.leo_scream.flux.AbstractAction;
import com.github.leo_scream.java_se_course.unit_04.task_04.business.Movie;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class AddMovieAction extends AbstractAction {

    private final Movie movie;

    public AddMovieAction(Movie movie) {
        super();
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
