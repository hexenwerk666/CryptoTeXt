package de.hsduesseldorf.medien.securesystems.editor.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsDialogController implements Initializable {

    @FXML
    ComboBox chipherSelection;

    @FXML
    ComboBox blockModeSelection;

    @FXML
    ComboBox paddingSelection;

    @FXML
    Button submitOptions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
