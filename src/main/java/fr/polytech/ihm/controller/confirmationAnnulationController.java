package fr.polytech.ihm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class confirmationAnnulationController {

    @FXML
    private Button YesButton;

    @FXML
    private Button NoButton;

    @FXML
    public void initialize() {
        Stage stage = (Stage) YesButton.getScene().getWindow();
        stage.close();


    }
}
