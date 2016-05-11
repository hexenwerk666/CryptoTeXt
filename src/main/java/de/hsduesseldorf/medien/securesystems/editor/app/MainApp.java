package de.hsduesseldorf.medien.securesystems.editor.app;

import de.hsduesseldorf.medien.securesystems.editor.controller.EditorController;
import de.hsduesseldorf.medien.securesystems.editor.controller.OptionsDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private Stage optionsDialog;

    private OptionsDialogController optionsDialogController;
    private EditorController editorController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CryptoTeXt");
        initRootLayout();
        initOptionsDialog();
        initDependencies();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/editor.fxml"));
            AnchorPane element = loader.load();
            this.editorController = loader.getController();
            Scene root = new Scene(element);
            this.primaryStage.setScene(root);
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initOptionsDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/optionsDialog.fxml"));
            VBox element = (VBox) loader.load();
            this.optionsDialogController = loader.getController();
            this.optionsDialog = new Stage();
            this.optionsDialog.initModality(Modality.APPLICATION_MODAL);
            this.optionsDialog.setScene(new Scene(element));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initDependencies() {
        this.editorController.setOptionsDialogController(this.optionsDialogController);
        this.editorController.setMainApp(this);
        this.optionsDialogController.setEditorController(this.editorController);
        this.optionsDialogController.setMainApp(this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Stage getOptionsDialog() {
        return optionsDialog;
    }
}
