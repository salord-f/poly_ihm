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
        Stage stage = (Stage) new_declaration_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    void ok_button_clicked(MouseEvent event) {
        String fxmlFile = "/fxml/viewIncidents.fxml";
        FXMLLoader loader = new FXMLLoader();
        try {
            Stage stage = (Stage) ok_button.getScene().getWindow();
            Stage stage2 = (Stage) stage.getOwner();
            Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            Scene scene = new Scene(rootNode);
            stage2.setScene(scene);
            scene.getStylesheets().add("/styles/styles.css");
            stage.close();
            stage2.setTitle("Liste des déclarations");
            stage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
