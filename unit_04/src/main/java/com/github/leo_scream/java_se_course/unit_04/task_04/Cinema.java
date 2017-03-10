package com.github.leo_scream.java_se_course.unit_04.task_04;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Cinema extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        InputStream inputStream = getClass().getResourceAsStream("views/main.fxml");
        AnchorPane root = loader.load(inputStream);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(400);
        primaryStage.setTitle("Cinema");
        primaryStage.show();
    }
}
