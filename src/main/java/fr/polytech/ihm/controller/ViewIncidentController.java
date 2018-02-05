package fr.polytech.ihm.controller;

import fr.polytech.ihm.model.Incident;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ViewIncidentController {

    @FXML
    private TextField rechercherIncident;

    @FXML
    private Button declarerIncident;

    @FXML
    private GridPane trierPar;

    @FXML
    private Label trieUrgence;

    @FXML
    private Label trieDate;

    @FXML
    private Label trieLieu;

    @FXML
    private Label trieCat;

    @FXML
    private ListView<Incident> listeViewIncidents;

}
