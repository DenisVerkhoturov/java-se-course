package com.github.leo_scream.java_se_course.unit_04.task_04.components;

import com.github.leo_scream.java_se_course.unit_04.task_04.business.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Main {

    private static volatile Main instance;

    @FXML
    public Button deleteButton;
    @FXML
    public Button editButton;
    @FXML
    public Button addButton;
    @FXML
    private GridPane root;
    @FXML
    private ListView<Movie> moviesList;
    @FXML
    private VBox movieEditor;

    private Main() {
    }

    public static Main getInstance() {
        Main localInstance = instance;
        if (localInstance == null) {
            synchronized (Main.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new Main();
                }
            }
        }
        return localInstance;
    }

    public void initialize() {
        addButton.setOnMouseClicked(
            event -> {
                MovieEditor.getInstance().setMovie(new Movie());
                MoviesList.getInstance().minimize();
                MovieEditor.getInstance().maximize();
            }
        );
        editButton.setOnMouseClicked(
            event -> {
                MovieEditor.getInstance().setMovie(moviesList.getSelectionModel().getSelectedItem());
                MoviesList.getInstance().minimize();
                MovieEditor.getInstance().maximize();
            }
        );
        addButton.disableProperty()
            .bind(movieEditor.visibleProperty());
        editButton.disableProperty()
            .bind(moviesList.getSelectionModel().selectedItemProperty().isNull());
        deleteButton.disableProperty()
            .bind(moviesList.getSelectionModel().selectedItemProperty().isNull());
    }

    public double percentageWidth(double percent) {
        double padding = root.getPadding().getLeft() + root.getPadding().getRight();
        return (root.getWidth() - padding) * percent;
    }

    GridPane getGridPane() {
        return root;
    }
}
