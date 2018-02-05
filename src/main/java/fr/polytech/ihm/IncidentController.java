package fr.polytech.ihm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class IncidentController {

    @FXML
    private GridPane incident;

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
    private Label voirDescription;

    @FXML
    private Label descriptionIncident;

    @FXML
    private ImageView imageIncident;

    @FXML
    private Label emailTitle;

    @FXML
    private Label email;

}
