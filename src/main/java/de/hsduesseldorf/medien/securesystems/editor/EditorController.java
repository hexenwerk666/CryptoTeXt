package de.hsduesseldorf.medien.securesystems.editor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    @FXML
    Button helloButton;

    @FXML
    Button clearButton;

    @FXML
    Label label;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.helloButton.setOnAction((event)->{
            label.setText("Hello World!");
        });

        this.clearButton.setOnAction((event -> {
            label.setText("");
        }));
    }
}
