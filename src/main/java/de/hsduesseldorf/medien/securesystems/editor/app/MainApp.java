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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CryptoTeXt");
        initRootLayout();
        initOptionsDialog();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/editor.fxml"));
            AnchorPane element = loader.load();
            EditorController editorController = loader.getController();
            editorController.setMainApp(this);
            Scene root = new Scene(element);
            primaryStage.setScene(root);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initOptionsDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/optionsDialog.fxml"));
            VBox element = (VBox) loader.load();
            OptionsDialogController optionsDialogController = loader.getController();
            optionsDialogController.setMainApp(this);
            optionsDialog = new Stage();
            optionsDialog.initModality(Modality.APPLICATION_MODAL);
            optionsDialog.setScene(new Scene(element));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Stage getOptionsDialog() {
        return optionsDialog;
    }
}
