package com.github.leo_scream.java_se_course.unit_03.task_02;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.InputStream;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class FAQ extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        InputStream inputStream = getClass().getResourceAsStream("views/main.fxml");
        AnchorPane root = loader.load(inputStream);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(400);
        primaryStage.setTitle("FAQ");
        primaryStage.show();
    }
}