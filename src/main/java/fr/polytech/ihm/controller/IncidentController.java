package fr.polytech.ihm.controller;

import fr.polytech.ihm.model.Incident;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class IncidentController {

    @FXML
    private GridPane infoGlobals;

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
    private GridPane infoSupp;

    @FXML
    private Label description;

    @FXML
    private Label detailLieu;

    @FXML
    private ImageView imageIncident;

    @FXML
    private Label emailTitle;

    @FXML
    private Label email;

    @FXML
    public void initialize(Incident incident) {
        //urgenceIcon.setImage(orange);
        infoSupp.setManaged(!infoSupp.isManaged());
        infoSupp.setVisible(!infoSupp.isVisible());
        voirDescription.setOnAction(event -> {
            infoSupp.setManaged(!infoSupp.isManaged());
            infoSupp.setVisible(!infoSupp.isVisible());
        });
    }
}
