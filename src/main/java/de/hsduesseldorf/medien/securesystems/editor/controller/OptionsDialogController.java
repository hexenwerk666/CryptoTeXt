package de.hsduesseldorf.medien.securesystems.editor.controller;

import de.hsduesseldorf.medien.securesystems.editor.app.MainApp;
import de.hsduesseldorf.medien.securesystems.editor.model.Options;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.BlockMode;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.CipherName;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.Padding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsDialogController implements Initializable {

    MainApp mainApp;
    EditorController editorController;

    @FXML
    ComboBox<CipherName> chipherSelection;

    @FXML
    ComboBox<BlockMode> blockModeSelection;

    @FXML
    ComboBox<Padding> paddingSelection;

    @FXML
    Button submitOptions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitOptions.setOnAction(e -> submit());
    }

    public void setEditorController(EditorController editorController) {
        this.editorController = editorController;
    }

    public void submit() {
        Options options = new Options(chipherSelection.getValue(), blockModeSelection.getValue(), paddingSelection.getValue());
        this.editorController.setSelectedOptions(options);
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
