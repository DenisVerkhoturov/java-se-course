package com.github.leo_scream.java_se_course.unit_05.task_01.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.StringConverter;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class FileSystem {

    private static volatile FileSystem instance;
    private final TreeItem<File> rootItem;
    @FXML
    private TreeView<File> filesTree;

    private FileSystem() {
        rootItem = new TreeItem<>(new File(System.getProperty("user.home")));
    }

    public static FileSystem getInstance() {
        FileSystem localInstance = instance;
        if (localInstance == null) {
            synchronized (FileSystem.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new FileSystem();
                }
            }
        }
        return localInstance;
    }

    public void initialize() {
        filesTree.setRoot(rootItem);
        filesTree.setShowRoot(false);
        rootItem.setExpanded(true);
        loadDirectory(rootItem);

        filesTree.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> Optional.ofNullable(newValue).ifPresent(
                item -> {
                    if (item.getValue().isDirectory() && item.getChildren().isEmpty()) {
                        loadDirectory(item);
                    }
                }
            )
        );

        filesTree.setOnMouseClicked(
            event -> {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    File selectedFile = filesTree.getSelectionModel().getSelectedItem().getValue();
                    if (selectedFile.isFile()) {
                        FileEditor.getInstance().openFile(selectedFile);
                    }
                }
            }
        );

        filesTree.setOnKeyReleased(
            event -> {
                File selectedFile = filesTree.getSelectionModel().getSelectedItem().getValue();
                if (selectedFile.isFile() && event.getCode().equals(KeyCode.ENTER)) {
                    FileEditor.getInstance().openFile(selectedFile);
                }
            }
        );

        filesTree.setCellFactory(
            param -> new TextFieldTreeCell<>(
                new StringConverter<File>() {
                    @Override
                    public String toString(File file) {
                        return file.getName();
                    }

                    @Override
                    public File fromString(String string) {
                        return new File(string);
                    }
                }
            )
        );
    }

    private void loadDirectory(final TreeItem<File> directory) {
        try {
            Files.list(directory.getValue().toPath())
                .filter(path -> !path.toFile().isHidden())
                .forEach(
                    path -> {
                        TreeItem<File> item = new TreeItem<>(path.toFile());
                        directory.getChildren().add(item);
                    }
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
