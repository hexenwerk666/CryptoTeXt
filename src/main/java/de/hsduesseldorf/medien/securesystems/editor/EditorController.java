package de.hsduesseldorf.medien.securesystems.editor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    @FXML
    MenuItem menuFileSaveAs;

    @FXML
    MenuItem menuFileSave;

    @FXML
    MenuItem menuFileLoad;

    @FXML
    MenuItem menuFileQuit;

    @FXML
    TextArea text;

    File currentFile;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuFileSaveAs.setOnAction((event) -> saveAs());
        menuFileSave.setOnAction((event) -> save());
        menuFileLoad.setOnAction((event) -> load());
        menuFileQuit.setOnAction((event) -> quit());
    }

    private boolean saveAs() {
        FileChooser fileChooser = new FileChooser();
        if (currentFile != null) {
            fileChooser.setInitialDirectory(currentFile.getParentFile());
            fileChooser.setInitialFileName(currentFile.getName());
        }
        fileChooser.setTitle("Save file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"), new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showSaveDialog(new Stage());
        if (file == null) return false;
        byte[] data = text.getText().getBytes();
        try {
            Files.write(file.toPath(), data);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean save() {
        if (currentFile == null) {
            return this.saveAs();
        }

        byte[] data = text.getText().getBytes();
        try {
            Files.write(currentFile.toPath(), data);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean load() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"), new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showOpenDialog(new Stage());
        try {
            byte[] data = Files.readAllBytes(file.toPath());
            text.setText(new String(data));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        currentFile = file;
        return true;
    }

    private void quit() {
        Platform.exit();
        System.exit(0);
    }
}
