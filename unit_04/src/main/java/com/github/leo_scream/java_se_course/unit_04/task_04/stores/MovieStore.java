package com.github.leo_scream.java_se_course.unit_04.task_04.stores;

import com.github.leo_scream.flux.AbstractStore;
import com.github.leo_scream.flux.Action;
import com.github.leo_scream.flux.Dispatcher;
import com.github.leo_scream.java_se_course.unit_04.task_04.actions.AddMovieAction;
import com.github.leo_scream.java_se_course.unit_04.task_04.actions.DeleteMovieAction;
import com.github.leo_scream.java_se_course.unit_04.task_04.actions.UpdateMovieAction;
import com.github.leo_scream.java_se_course.unit_04.task_04.business.Movie;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class MovieStore extends AbstractStore {

    private static volatile MovieStore instance;
    private final Set<Movie> movies;
    private Movie currentMovie;

    private MovieStore()
    {
        movies = new HashSet<>();
        Dispatcher.getInstance().register(this);
    }

    @Override
    public void processAction(Action action) {
        if (action instanceof AddMovieAction) {
            processAddMovieAction((AddMovieAction) action);
        }
        if (action instanceof DeleteMovieAction) {
            processDeleteMovieAction((DeleteMovieAction) action);
        }
        if (action instanceof UpdateMovieAction) {
            processUpdateMovieAction((UpdateMovieAction) action);
        }
    }

    private void processAddMovieAction(AddMovieAction action) {
        System.out.println("Action: Adding new movie");
        movies.add(action.getMovie());
        publishOnChange();
    }

    private void processDeleteMovieAction(DeleteMovieAction action) {
        System.out.println("Action: Deleting new movie");
        movies.remove(action.getMovie());
        publishOnChange();
    }

    private void processUpdateMovieAction(UpdateMovieAction action) {
        System.out.println("Action: Updating new movie");
        movies.remove(action.getOldMovie());
        movies.add(action.getNewMovie());
        publishOnChange();
    }

    public static MovieStore getInstance() {
        MovieStore localInstance = instance;
        if (localInstance == null) {
            synchronized (MovieStore.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new MovieStore();
                }
            }
        }
        return localInstance;
    }

    public Stream<Movie> getMovies() {
        return movies.stream();
    }
}
