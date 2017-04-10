package com.github.leo_scream.java_se_course.unit_05.task_01.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class FileEditor {

    private static volatile FileEditor instance;

    @FXML
    private TabPane tabPane;

    private Map<File, Tab> fileTabMap;

    private FileEditor() {
        fileTabMap = new HashMap<>();
    }

    public static FileEditor getInstance() {
        FileEditor localInstance = instance;
        if (localInstance == null) {
            synchronized (FileEditor.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new FileEditor();
                }
            }
        }
        return localInstance;
    }

    public void initialize() {
        tabPane.setOnKeyPressed(
            event -> {
                if (event.isControlDown() && event.getCode().equals(KeyCode.S)) {
                    Tab tabToSave = tabPane.getSelectionModel().getSelectedItem();
                    fileTabMap.entrySet().stream()
                        .filter(entry -> tabToSave.equals(entry.getValue()))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .ifPresent(
                            file -> saveFile(file, ((TextArea) tabToSave.getContent()).getText())
                        );
                }
            }
        );
    }

    public void openFile(final File file) {
        Tab fileTab = fileTabMap.containsKey(file) ? fileTabMap.get(file) : createTab(file);
        tabPane.getSelectionModel().select(fileTab);
    }

    private Tab createTab(final File file) {
        Tab tab = new Tab();
        tab.setOnClosed(this::onTabClosed);
        tab.setText(file.getName());
        TextArea textArea = new TextArea();
        tab.setContent(textArea);
        try {
            String contentType = Files.probeContentType(file.toPath());
            if (contentType != null && contentType.startsWith("text/")) {
                String fileContent = new String(Files.readAllBytes(file.toPath()));
                textArea.setText(fileContent);
                fileTabMap.put(file, tab);
                tabPane.getTabs().add(tab);
                tabPane.getSelectionModel().select(tab);
            }

        } catch (IOException e) {
            Dialog dialog = new Dialog();
            DialogPane dialogPane = dialog.getDialogPane();

            dialogPane.getButtonTypes().addAll(ButtonType.OK);
            dialogPane.setHeaderText("Something goes wrong");
            dialogPane.setContentText("I/O exception");

            dialog.showAndWait();
        }
        return tab;
    }

    private void onTabClosed(Event event) {
        Tab tab = (Tab) event.getTarget();
        fileTabMap.entrySet().removeIf(entry -> tab.equals(entry.getValue()));
    }

    private void saveFile(final File file, final String content) {
        try {
            Files.write(
                file.toPath(),
                content.getBytes(),
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
            );
        } catch (IOException e) {
            Dialog dialog = new Dialog();
            DialogPane dialogPane = dialog.getDialogPane();

            dialogPane.getButtonTypes().addAll(ButtonType.OK);
            dialogPane.setHeaderText("Something goes wrong");
            dialogPane.setContentText("I/O exception");

            dialog.showAndWait();
        }
    }
}
