package de.hsduesseldorf.medien.securesystems.editor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    public static final String STATIC_FILE_PATH = "/tmp/editorTest";
    @FXML
    MenuItem menuFileSave;

    @FXML
    MenuItem menuFileLoad;

    @FXML
    MenuItem menuFileQuit;

    @FXML
    TextArea text;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuFileSave.setOnAction((event) -> save());
        menuFileLoad.setOnAction((event) -> load());
        menuFileQuit.setOnAction((event) -> quit());
    }

    private boolean save() {
        byte[] data = text.getText().getBytes();
        Path file = Paths.get(STATIC_FILE_PATH);
        try {
            Files.write(file, data);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean load() {
        Path file = Paths.get(STATIC_FILE_PATH);
        try {
            byte[] data = Files.readAllBytes(file);
            text.setText(new String(data));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    private void quit() {
        Platform.exit();
        System.exit(0);
    }
}
