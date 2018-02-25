package fr.polytech.ihm.controller;

import fr.polytech.ihm.VoirDescriptionTransition;
import fr.polytech.ihm.model.Incident;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class IncidentController {
    private Incident incident;
    private VoirDescriptionTransition t;
    private FadeTransition fade;

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
    private Label detailLieu2;

    @FXML
    private Label email;

    @FXML
    private Button voirImage;

	/**
	 * Initializes the animation for "Voir description"
	 * and fills the incident passed as parameter
	 */
	@FXML
	public void initialize(Incident incident) {
		this.incident = incident;
		fill(this.incident);

        t = new VoirDescriptionTransition(Duration.millis(500), infoSupp);
        t.setOnFinished(event -> {
            if (infoSupp.getPrefHeight() != 0) infoSupp.setVisible(true);
        });

        infoSupp.setVisible(false);
        infoSupp.setPrefHeight(0);

        voirDescription.setOnAction(event -> {
            if (infoSupp.getPrefHeight() != 0)
                infoSupp.setVisible(false);
            t.setUp();
            t.play();
        });

        this.voirImage.addEventHandler(MouseEvent.MOUSE_PRESSED,
                e -> openImage());
    }

	/**
	 * Fills the incident with the correct informations
	 */
	private void fill(Incident incident) {
		this.date.setText(incident.getDate());

        String category = incident.getCategory().getName();
        if (category.equals("")) {
            this.categorie.setText("Pas de catégorie");
        } else this.categorie.setText(category);

        this.titreIncident.setText(incident.getTitle());

        String description = incident.getDescription();
        if (description.equals("")) {
            this.description.setText("Pas de description");
        } else this.description.setText(description);

        this.detailLieu.setText(incident.getLocation().getName());
        String detailLieu = incident.getLocation().getName();
        if (detailLieu.equals("")) {
            this.detailLieu.setText("Pas de details");
        } else this.detailLieu.setText(detailLieu);


        String locationDetail = incident.getLocationDetail();
        if (locationDetail.equals("")) {
            this.detailLieu2.setText("Pas de détail pour le lieu");
        } else this.detailLieu2.setText("Détails du lieu : " + locationDetail);

        this.email.setText(incident.getEmail() + incident.getEmailDomain());

        String image = incident.getImage();
        if (image.equals("")) voirImage.setVisible(false);

        switch (incident.getEmergency()) {
            case LOW:
                showUrgency("images" + File.separator + "green.png", urgenceIcon);
                break;
            case MEDIUM:
                showUrgency("images" + File.separator + "orange.png", urgenceIcon);
                break;
            case HIGH:
                showUrgency("images" + File.separator + "red.png", urgenceIcon);
                break;
            default:
                break;
        }
    }

	/**
	 * Enable the urgency images to showup
	 */
	private void showUrgency(String url, ImageView view) {
		try {
			Image image = new Image(url);
			view.setImage(image);
			view.setCache(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a new window showing the image corresponding
	 * to the incident image.
	 */
	@FXML
	void openImage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			Stage stage = new Stage();
			Stage stage2 = (Stage) voirImage.getScene().getWindow();
			Parent root = loader.load(getClass().getResourceAsStream("/fxml/image.fxml")); //TODO
			Scene s = new Scene(root);
			stage.setScene(s);
			s.getStylesheets().add("/styles/styles.css");
			stage.setTitle("Image");
			((ImageController) loader.getController()).initialize(incident.getImage());
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(stage2);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
