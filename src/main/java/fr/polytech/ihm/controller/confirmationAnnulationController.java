package fr.polytech.ihm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class confirmationAnnulationController {

    @FXML
    private Button YesButton;

    @FXML
    private Button NoButton;

    @FXML
    public void NoButtonAction(ActionEvent event) {
        Stage stage = (Stage) YesButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void YesButtonAction(ActionEvent event){ //TODO
        try {
            Stage stage = (Stage) YesButton.getScene().getWindow();

            Stage stage2 = (Stage) stage.getOwner();
            stage2.close();

            Parent root = FXMLLoader.load(
                    getClass().getResource("/fxml/viewIncidents.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("ViewIncident");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
