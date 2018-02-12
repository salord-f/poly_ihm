package fr.polytech.ihm.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUpController {
    @FXML
    private Button ok_button;

    @FXML
    private Button new_declaration_button;

    @FXML
    void new_declaration_button_clicked(MouseEvent event) {
        String fxmlFile = "/fxml/declaration.fxml";
        FXMLLoader loader = new FXMLLoader();
        try {
            Stage stage = (Stage) new_declaration_button.getScene().getWindow();
            Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

            Scene scene = new Scene(rootNode);
            stage.setScene(scene);
            ((DeclarationController) loader.getController()).initialize();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ok_button_clicked(MouseEvent event) {
        String fxmlFile = "/fxml/viewIncidents.fxml";
        FXMLLoader loader = new FXMLLoader();
        try {
            Stage stage = (Stage) ok_button.getScene().getWindow();
            Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

            Scene scene = new Scene(rootNode);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
