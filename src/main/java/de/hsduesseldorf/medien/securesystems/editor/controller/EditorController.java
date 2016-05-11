package de.hsduesseldorf.medien.securesystems.editor.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    @FXML
    MenuItem menuFileSave;

    @FXML
    MenuItem menuFileSaveAs;

    @FXML
    MenuItem menuFileOpen;

    @FXML
    MenuItem menuFileQuit;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuFileOpen.setOnAction(e -> open());
        menuFileSave.setOnAction(e -> save());
        menuFileSaveAs.setOnAction(e -> saveAs());
        menuFileQuit.setOnAction(e -> quit());
    }


    void save() {

    }

    void saveAs() {

    }

    void open() {

    }

    void quit() {
    }
}
