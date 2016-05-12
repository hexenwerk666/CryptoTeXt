package de.hsduesseldorf.medien.securesystems.editor.controller;

import de.hsduesseldorf.medien.securesystems.editor.app.MainApp;
import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.model.Options;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    static final Logger LOG = LoggerFactory.getLogger(EditorController.class);

    MainApp mainApp;
    OptionsDialogController optionsDialogController;
    Document currentDocument;


    @FXML
    MenuItem menuFileSave;

    @FXML
    MenuItem menuFileSaveAs;

    @FXML
    MenuItem menuFileOpen;

    @FXML
    MenuItem menuFileQuit;

    @FXML
    TextArea text;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuFileOpen.setOnAction(e -> open());
        menuFileSave.setOnAction(e -> save());
        menuFileSaveAs.setOnAction(e -> saveAs());
        menuFileQuit.setOnAction(e -> quit());
    }

    public void setOptionsDialogController(OptionsDialogController optionsDialogController) {
        this.optionsDialogController = optionsDialogController;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    boolean save() {
        LOG.debug("Save..");
        if (currentDocument == null) {
            return this.saveAs();
        }

        optionsDialogController.setSelectedOptions(new Options(currentDocument.getCipherName(), currentDocument.getBlockMode(), currentDocument.getPadding()));
        mainApp.getOptionsDialog().showAndWait();

        byte[] data = text.getText().getBytes();
        Document document = new Document(new Date(), optionsDialogController.getSelectedOptions(), 256, data.length, data, currentDocument.getFile());

        try {
            JAXB.marshal(document, currentDocument.getFile());
        } catch (DataBindingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    boolean saveAs() {
        LOG.debug("Save as..");
        mainApp.getOptionsDialog().showAndWait();
        FileChooser fileChooser = new FileChooser();
        if (currentDocument != null) {
            fileChooser.setInitialDirectory(currentDocument.getFile().getParentFile());
            fileChooser.setInitialFileName(currentDocument.getFile().getName());
        } else {
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        }
        fileChooser.setTitle("Save file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"), new FileChooser.ExtensionFilter("All Files", "*"));
        File file = fileChooser.showSaveDialog(new Stage());
        if (file == null) return false;

        byte[] data = text.getText().getBytes();
        Document document = new Document(new Date(), optionsDialogController.getSelectedOptions(), 256, data.length, data, file);

        try {
            JAXB.marshal(document, file);
            currentDocument = document;
            currentDocument.setFile(file);
        } catch (DataBindingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    boolean open() {
        LOG.debug("Open..");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"), new FileChooser.ExtensionFilter("All Files", "*"));
        fileChooser.setInitialDirectory(currentDocument != null ? currentDocument.getFile().getParentFile() : new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file == null) return false;
        try {
            Document document = JAXB.unmarshal(file, Document.class);
            currentDocument = document;
            byte[] data = document.getPayload();
            text.setText(new String(data));
            optionsDialogController.setSelectedOptions(document.getOptions());
        } catch (DataBindingException e) {
            e.printStackTrace();
            return false;
        }
        currentDocument.setFile(file);
        return true;
    }

    void quit() {
        LOG.debug("Quit..");
        System.exit(0);
    }
}
