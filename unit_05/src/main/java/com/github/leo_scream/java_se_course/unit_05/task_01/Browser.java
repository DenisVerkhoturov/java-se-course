package com.github.leo_scream.java_se_course.unit_05.task_01;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Browser extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/main.fxml"));
        loader.setControllerFactory(this::singletonFactory);
        final Parent root = loader.load();

        primaryStage.setTitle("Browser");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private Object singletonFactory(Class<?> controllerClass) {
        List<Method> methods = Arrays.asList(controllerClass.getMethods());

        try {
            for (Method method : methods) {
                if ("getInstance".equals(method.getName())) {
                    return method.invoke(null);
                }
            }
            return controllerClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Can't instantiate controller " + controllerClass.getName(), e);
        }
    }
}
