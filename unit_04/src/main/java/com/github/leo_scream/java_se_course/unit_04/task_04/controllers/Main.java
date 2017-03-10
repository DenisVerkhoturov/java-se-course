package com.github.leo_scream.java_se_course.unit_04.task_04.controllers;

import com.github.leo_scream.java_se_course.unit_04.task_04.Actor;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.SetProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Main implements Initializable {

    private final ObservableList<Movie> movies = FXCollections.observableArrayList();
    @FXML
    private Button newMovie;
    @FXML
    private ListView movieList;
    @FXML
    private VBox movieCard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        movieList.setItems(movies);
        newMovie.setOnAction(event -> {
            movieCard.getChildren().clear();
            System.out.println("New movie");
        });
    }

    private class Movie extends Parent {

        StringProperty name;
        SetProperty<Actor> actors;

        Movie() {
            this(new String(), new HashSet<Actor>());
        }

        Movie(String name, Set<Actor> actors) {

        }
    }

    private class MovieCard {

    }
}
