package de.hsduesseldorf.medien.securesystems.editor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controller for {@code editor.fxml}
 * <p>
 * Handle menu actions and events
 */
public class EditorController implements Initializable {

    @FXML
    MenuItem menuFileSaveAs;

    @FXML
    MenuItem menuFileSave;

    @FXML
    MenuItem menuFileOpen;

    @FXML
    MenuItem menuFileQuit;

    @FXML
    ComboBox<CipherOptions> cipherOptionsComboBox;

    @FXML
    TextArea text;

    File currentFile;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cipherOptionsComboBox.getItems().setAll(CipherOptions.values());
        menuFileSaveAs.setOnAction((event) -> saveAs());
        menuFileSave.setOnAction((event) -> save());
        menuFileOpen.setOnAction((event) -> load());
        menuFileQuit.setOnAction((event) -> quit());
    }

    /**
     * save the file and choose the location via FileChooser
     *
     * @return {@code true} if a file has been saved, otherwise {@code false}
     */
    private boolean saveAs() {
        FileChooser fileChooser = new FileChooser();
        if (currentFile != null) {
            fileChooser.setInitialDirectory(currentFile.getParentFile());
            fileChooser.setInitialFileName(currentFile.getName());
        } else {
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        }
        fileChooser.setTitle("Save file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"), new FileChooser.ExtensionFilter("All Files", "*"));
        File file = fileChooser.showSaveDialog(new Stage());
        if (file == null) return false;

        byte[] data = text.getText().getBytes();
        Document document = new Document(new Date(), cipherOptionsComboBox.getValue(), data);

        try {
            JAXB.marshal(document, file);
            currentFile = file;
        } catch (DataBindingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * save the current file
     *
     * @return {@code true} if a file has been saved, otherwise {@code false}
     */
    private boolean save() {
        if (currentFile == null) {
            return this.saveAs();
        }

        byte[] data = text.getText().getBytes();
        Document document = new Document(new Date(), cipherOptionsComboBox.getValue(), data);

        try {
            JAXB.marshal(document, currentFile);
        } catch (DataBindingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * load a file via FileChooser
     *
     * @return {@code true} if a file has been loaded, otherwise {@code false}
     */
    private boolean load() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"), new FileChooser.ExtensionFilter("All Files", "*"));
        fileChooser.setInitialDirectory(currentFile != null ? currentFile.getParentFile() : new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file == null) return false;
        try {
            Document document = JAXB.unmarshal(file, Document.class);
            byte[] data = document.getPayload();
            cipherOptionsComboBox.setValue(document.getCipherOptions());
            text.setText(new String(data));
        } catch (DataBindingException e) {
            e.printStackTrace();
            return false;
        }
        currentFile = file;
        return true;
    }

    /**
     * quit application
     */
    private void quit() {
        Platform.exit();
        System.exit(0);
    }
}
