package com.github.leo_scream.java_se_course.unit_04.task_04.business;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Movie implements Serializable {

    private final String name;
    private final Set<Actor> actors;

    public Movie() {
        name = "";
        actors = new HashSet<>();
    }

    private Movie(String name, Set<Actor> actors) {
        this.name = name;
        this.actors = actors;
    }

    public Movie withName(String name) {
        Objects.requireNonNull(name);
        return new Movie(name, this.actors);
    }

    public Movie withActor(Actor actor) {
        Objects.requireNonNull(actor);
        Set<Actor> actors = new HashSet<>(this.actors);
        actors.add(actor);
        return new Movie(this.name, actors);
    }

    public Movie withActors(Set<Actor> actors) {
        Objects.requireNonNull(actors);
        actors.forEach(Objects::requireNonNull);

        return new Movie(this.name, actors);
    }

    public Stream<Actor> getActors() {
        return this.actors.stream();
    }
}
