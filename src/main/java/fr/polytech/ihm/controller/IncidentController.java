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
    private Label categorie;

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

        fill(incident);
        infoSupp.setManaged(!infoSupp.isManaged());
        infoSupp.setVisible(!infoSupp.isVisible());
        voirDescription.setOnAction(event -> {
            infoSupp.setManaged(!infoSupp.isManaged());
            infoSupp.setVisible(!infoSupp.isVisible());
        });
    }

    private void fill(Incident incident) {
        this.date.setText(incident.getDate());

        String categorie = incident.getCategorie();
        if (categorie.equals("")) {
            this.categorie.setText("No category");
        } else this.categorie.setText(categorie);

        this.titreIncident.setText(incident.getTitre());

        String description = incident.getDescription();
        if (description.equals("")) {
            this.description.setText("No description");
        } else this.description.setText(description);

        this.detailLieu.setText(incident.getLocalisation());
        String detailLieu = incident.getLocalisation();
        if (detailLieu.equals("")) {
            this.detailLieu.setText("No detail");
        } else this.detailLieu.setText(detailLieu);

        this.email.setText(incident.getEmail() + incident.getEmailDomaine());

        String image = incident.getImage();
        if (!image.equals("")) {
            this.imageIncident = new ImageView(image);
        }

    }
}
