package fr.polytech.ihm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class IncidentController {

    @FXML
    private VBox incident;

    @FXML
    private GridPane infoGlobals;

    @FXML
    private HBox infoSupp;

    @FXML
    private Label date;

    @FXML
    private Label voiture;

    @FXML
    private ImageView urgenceIcon;

    @FXML
    private ImageView lieuIcon;

    @FXML
    private Label titreIncident;

    @FXML
    private Button voirDescription;

    @FXML
    private Label Description;

    @FXML
    private Label DetailLieu;

    @FXML
    private Label descriptionIncident;

    @FXML
    private ImageView imageIncident;

    @FXML
    private Label emailTitle;

    @FXML
    private Label email;

    private Image orange = new Image("file:./src/main/resources/images/orange.png");

    @FXML
    public void initialize() {
        urgenceIcon.setImage(orange);

        voirDescription.setOnAction(event -> {
            infoSupp.setManaged(!infoSupp.isManaged());
            infoSupp.setVisible(!infoSupp.isVisible());
        });

    }
}
