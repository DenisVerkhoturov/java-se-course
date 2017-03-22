package com.github.leo_scream.java_se_course.unit_04.task_04.actions;

import com.github.leo_scream.java_se_course.unit_04.flux.AbstractAction;
import com.github.leo_scream.java_se_course.unit_04.task_04.business.Movie;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class DeleteMovieAction extends AbstractAction {
    private final Movie movie;

    public DeleteMovieAction(Movie movie) {
        super();
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
