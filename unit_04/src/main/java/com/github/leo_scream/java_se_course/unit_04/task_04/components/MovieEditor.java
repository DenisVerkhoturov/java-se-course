package com.github.leo_scream.java_se_course.unit_04.task_04.components;

import com.github.leo_scream.flux.Dispatcher;
import com.github.leo_scream.java_se_course.unit_04.task_04.actions.AddMovieAction;
import com.github.leo_scream.java_se_course.unit_04.task_04.business.Actor;
import com.github.leo_scream.java_se_course.unit_04.task_04.business.Movie;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class MovieEditor {

    private static volatile MovieEditor instance;

    @FXML
    public VBox movieEditor;
    @FXML
    private ListView<Actor> actorsList;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField actorTextField;
    @FXML
    private TextField titleTextField;

    private final ObjectProperty<Movie> movie;

    private MovieEditor() {
        movie = new SimpleObjectProperty<>(new Movie());
    }

    public void initialize() {
        cancelButton.setOnMouseClicked(event -> {
            this.minimize();
            MoviesList.getInstance().maximize();
            clearForm();
        });

        saveButton.setOnMouseClicked(event -> saveMovie());

        saveButton.disableProperty().setValue(false);

        titleTextField.setOnKeyReleased(event -> isFormValid());

        actorTextField.setOnKeyReleased(
            event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    actorsList.getItems().add(new Actor().withName(actorTextField.getText()));
                    actorTextField.clear();
                }
            }
        );
    }

    private void saveMovie() {
        if (isFormValid()) {
            movie.setValue(movie.getValue().withName(titleTextField.getText()));
            Set<Actor> actors = actorsList.getItems().stream().collect(Collectors.toSet());
            movie.setValue(movie.getValue().withActors(actors));
            Dispatcher.getInstance().dispatch(new AddMovieAction(movie.getValue()));
            clearForm();
            minimize();
            MoviesList.getInstance().maximize();
        }
    }

    private void clearForm() {
        titleTextField.clear();
        actorTextField.clear();
        actorsList.getItems().clear();
    }

    private boolean isFormValid() {
        boolean validity = true;
        if (titleTextField.getText() == null && titleTextField.getText().length() == 0) {
            validity = false;
        }
        if (actorsList.getItems().isEmpty()) {
            validity = false;
        }

        return validity;
    }

    void minimize() {
        Main.getInstance().getGridPane().setColumnSpan(movieEditor, 1);
        movieEditor.setVisible(false);
    }

    void maximize() {
        movieEditor.setVisible(true);
        Main.getInstance().getGridPane().setColumnSpan(movieEditor, 3);
    }

    void setMovie(Movie movie) {
        this.movie.setValue(movie);
    }

    public static MovieEditor getInstance() {
        MovieEditor localInstance = instance;
        if (localInstance == null) {
            synchronized (MovieEditor.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new MovieEditor();
                }
            }
        }
        return localInstance;
    }
}
